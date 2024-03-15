package com.anilyilmaz.n11casestudy.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anilyilmaz.n11casestudy.core.data.mapper.toUserEntity
import com.anilyilmaz.n11casestudy.core.database.N11Database
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
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val users = dataSource.searchUsers(
                query = query,
                page = loadKey,
                perPage = state.config.pageSize
            )

            n11Database.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    n11Database.userDao().clearUsers()
                }
                val beerEntities = users.items.map { it.toUserEntity() }
                n11Database.userDao().insertAll(beerEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = users.items.isEmpty()
            )
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}