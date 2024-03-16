package com.anilyilmaz.n11casestudy.core.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anilyilmaz.n11casestudy.core.data.mapper.toUserEntity
import com.anilyilmaz.n11casestudy.core.database.N11Database
import com.anilyilmaz.n11casestudy.core.database.entity.RemoteKeyEntity
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity
import com.anilyilmaz.n11casestudy.core.network.datasource.GithubDataSource
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(
    private val query: String,
    private val dataSource: GithubDataSource,
    private val n11Database: N11Database
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                nextKey
            }
        }

        try {
            Log.e("TAG", "ANILYILMAZ: " )
            val users = dataSource.searchUsers(
                query = query,
                page = page,
                perPage = state.config.pageSize
            ).items.map { it.toUserEntity() }
            val endOfPaginationReached = users.isEmpty()

            n11Database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    n11Database.remoteKeyDao().clearRemoteKeys()
                    n11Database.userDao().clearUsers()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = users.map {
                    RemoteKeyEntity(userId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                n11Database.remoteKeyDao().insertAll(keys)
                n11Database.userDao().insertAll(users)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { userId ->
                n11Database.remoteKeyDao().remoteKeysUserId(userId = userId)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { userEntity ->
                n11Database.remoteKeyDao().remoteKeysUserId(userId = userEntity.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { userEntity ->
                n11Database.remoteKeyDao().remoteKeysUserId(userId = userEntity.id)
            }
    }
}