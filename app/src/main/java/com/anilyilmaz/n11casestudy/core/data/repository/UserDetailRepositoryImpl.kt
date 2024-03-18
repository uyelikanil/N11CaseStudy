package com.anilyilmaz.n11casestudy.core.data.repository

import com.anilyilmaz.n11casestudy.core.data.mapper.toUserDetailEntity
import com.anilyilmaz.n11casestudy.core.database.N11Database
import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import com.anilyilmaz.n11casestudy.core.network.datasource.GithubDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(
    private val n11Database: N11Database,
    private val githubDataSource: GithubDataSource
): UserDetailRepository {
    override fun getUserDetail(login: String): Flow<UserDetailEntity?> = flow {
        try {
            val networkUserDetail = githubDataSource.getUserDetail(login)
            n11Database.userDetailDao().insert(networkUserDetail.toUserDetailEntity())
        } catch(e: Exception) {
            e.printStackTrace()
        } catch(e: IOException) {
            e.printStackTrace()
        }

        emitAll(n11Database.userDetailDao().userDetail(login))
    }
}