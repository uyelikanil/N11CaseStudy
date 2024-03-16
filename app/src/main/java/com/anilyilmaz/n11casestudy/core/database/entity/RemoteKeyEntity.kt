package com.anilyilmaz.n11casestudy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey val userId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)