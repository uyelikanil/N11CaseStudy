package com.anilyilmaz.n11casestudy.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUserSearch (
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("incomplete_results") val incompleteResults: Boolean = false,
    val items: List<NetworkUserSearchItem> = emptyList()
)