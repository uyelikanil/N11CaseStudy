package com.anilyilmaz.n11casestudy.core.domain.mapper

import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity
import com.anilyilmaz.n11casestudy.core.model.User

fun UserEntity.toUser(isFavorite: Boolean = false): User {
    return User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        isFavorite = isFavorite
    )
}