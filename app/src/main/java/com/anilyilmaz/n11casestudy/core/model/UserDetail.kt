package com.anilyilmaz.n11casestudy.core.model

data class UserDetail(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val name: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val isFavorite: Boolean
)
