package com.anilyilmaz.n11casestudy.core.data.mapper

import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity
import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserSearchItem

fun NetworkUserSearchItem.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        login = login,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        siteAdmin = siteAdmin,
        score= score
    )
}