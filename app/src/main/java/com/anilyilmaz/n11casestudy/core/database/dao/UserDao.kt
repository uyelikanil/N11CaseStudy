package com.anilyilmaz.n11casestudy.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<UserEntity>)

    @Query("SELECT * FROM users ORDER BY id desc")
    fun users(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM users")
    suspend fun clearUsers()
}