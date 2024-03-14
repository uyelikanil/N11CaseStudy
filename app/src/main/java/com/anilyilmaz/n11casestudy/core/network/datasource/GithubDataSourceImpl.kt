package com.anilyilmaz.n11casestudy.core.network.datasource

import androidx.paging.PagingData
import com.anilyilmaz.n11casestudy.BuildConfig
import com.anilyilmaz.n11casestudy.core.network.api.GithubService
import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserSearch
import kotlinx.coroutines.flow.Flow
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDataSourceImpl @Inject constructor(okhttpCallFactory: Call.Factory): GithubDataSource {
    private val githubApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubService::class.java)

    override suspend fun searchUsers(query: String, page: Int, perPage: Int): NetworkUserSearch =
        githubApi.searchUsers(query, page, perPage)
}