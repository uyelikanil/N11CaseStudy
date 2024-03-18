package com.anilyilmaz.n11casestudy.core.network.datasource

import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserDetail
import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserSearch

interface GithubDataSource {
    suspend fun searchUsers(query: String, page: Int, perPage: Int): NetworkUserSearch
    suspend fun getUserDetail(username: String): NetworkUserDetail
}