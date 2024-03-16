package com.anilyilmaz.n11casestudy.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.n11casestudy.core.data.GithubRemoteMediator
import com.anilyilmaz.n11casestudy.core.database.N11Database
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity
import com.anilyilmaz.n11casestudy.core.network.datasource.GithubDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSearchRepositoryImpl @Inject constructor(
    private val n11Database: N11Database,
    private val githubDataSource: GithubDataSource
): UserSearchRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun searchUsers(query: String): Flow<PagingData<UserEntity>> {
        val pagingSourceFactory = { n11Database.userDao().users() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = GithubRemoteMediator(
                query,
                githubDataSource,
                n11Database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}