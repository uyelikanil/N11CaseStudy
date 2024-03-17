package com.anilyilmaz.n11casestudy.core.model

data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val isFavorite: Boolean
)
