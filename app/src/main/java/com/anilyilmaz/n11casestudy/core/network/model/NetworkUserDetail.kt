package com.anilyilmaz.n11casestudy.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUserDetail (
    val login: String = "",
    val id: Long = 0,
    @SerializedName("node_id") val nodeId: String = "",
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("gravatar_id") val gravatarId: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
    @SerializedName("followers_url") val followersUrl: String = "",
    @SerializedName("following_url") val followingUrl: String = "",
    @SerializedName("gists_url") val gistsUrl: String = "",
    @SerializedName("starred_url") val starredUrl: String = "",
    @SerializedName("subscriptions_url") val subscriptionsUrl: String = "",
    @SerializedName("organizations_url") val organizationsUrl: String = "",
    @SerializedName("repos_url") val reposUrl: String = "",
    @SerializedName("events_url") val eventsUrl: String = "",
    @SerializedName("received_events_url") val receivedEventsUrl: String = "",
    val type: String = "",
    @SerializedName("site_admin") val siteAdmin: Boolean = false,
    val name: String = "",
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val email: String = "",
    val hireable: Boolean = false,
    val bio: String = "",
    @SerializedName("twitter_username") val twitterUserName: String = "",
    @SerializedName("public_repos") val publicRepos: Int = 0,
    @SerializedName("public_gists") val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    @SerializedName("created_at") val createdAt: String = "",
    @SerializedName("updated_at") val updatedAt: String = ""
)