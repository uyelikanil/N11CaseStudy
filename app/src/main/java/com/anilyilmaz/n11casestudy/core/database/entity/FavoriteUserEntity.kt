package com.anilyilmaz.n11casestudy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Long,
)