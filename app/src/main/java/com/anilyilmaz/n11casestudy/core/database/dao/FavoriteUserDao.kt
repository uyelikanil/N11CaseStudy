package com.anilyilmaz.n11casestudy.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anilyilmaz.n11casestudy.core.database.entity.FavoriteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToFavorites(favoriteUserEntity: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_users")
    fun favoriteUsers(): Flow<List<FavoriteUserEntity>>

    @Query("DELETE FROM favorite_users where userId = :userId")
    suspend fun deleteFromFavorites(userId: Long)
}