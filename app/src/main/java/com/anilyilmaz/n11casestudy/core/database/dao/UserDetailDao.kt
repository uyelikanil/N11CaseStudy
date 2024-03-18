package com.anilyilmaz.n11casestudy.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetail: UserDetailEntity)

    @Query("SELECT * FROM user_detail WHERE login = :login")
    fun userDetail(login: String): Flow<UserDetailEntity?>
}