package com.anilyilmaz.n11casestudy.core.network.api

import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): NetworkUserSearch
}