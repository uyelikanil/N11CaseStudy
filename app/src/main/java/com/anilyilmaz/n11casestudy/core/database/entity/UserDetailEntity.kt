package com.anilyilmaz.n11casestudy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class UserDetailEntity (
    @PrimaryKey
    val id: Long,
    val login: String,
    val nodeId: String?,
    val avatarUrl: String,
    val gravatarId: String?,
    val url: String?,
    val htmlUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val gistsUrl: String?,
    val starredUrl: String?,
    val subscriptionsUrl: String?,
    val organizationsUrl: String?,
    val reposUrl: String?,
    val eventsUrl: String?,
    val receivedEventsUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Boolean?,
    val bio: String?,
    val twitterUserName: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val createdAt: String?,
    val updatedAt: String?
)