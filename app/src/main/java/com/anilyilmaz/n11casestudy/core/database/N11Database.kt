package com.anilyilmaz.n11casestudy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anilyilmaz.n11casestudy.core.database.dao.UserDao
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity

@Database(
    entities = [ UserEntity::class ],
    version = 1,
    exportSchema = true,
)
abstract class N11Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}