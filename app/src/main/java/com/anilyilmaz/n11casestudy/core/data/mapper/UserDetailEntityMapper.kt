package com.anilyilmaz.n11casestudy.core.data.mapper

import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import com.anilyilmaz.n11casestudy.core.network.model.NetworkUserDetail

fun NetworkUserDetail.toUserDetailEntity(): UserDetailEntity {
    return UserDetailEntity(
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
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        hireable = hireable,
        bio = bio, twitterUserName = twitterUserName,
        publicRepos = publicRepos,
        publicGists = publicGists,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}