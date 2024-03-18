package com.anilyilmaz.n11casestudy.core.domain.mapper

import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import com.anilyilmaz.n11casestudy.core.model.UserDetail

fun UserDetailEntity.toUserDetail(isFavorite: Boolean = false): UserDetail {
    return UserDetail(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        name = name,
        publicRepos = publicRepos,
        followers =  followers,
        following = following,
        isFavorite = isFavorite
    )
}