package com.anilyilmaz.n11casestudy.core.data.repository

import com.anilyilmaz.n11casestudy.core.database.entity.FavoriteUserEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteUserRepository {
    suspend fun insertToFavorites(favoriteUserEntity: FavoriteUserEntity)

    fun favoriteUsers(): Flow<List<FavoriteUserEntity>>

    suspend fun deleteFromFavorites(userId: Long)
}