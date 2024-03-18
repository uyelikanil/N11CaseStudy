package com.anilyilmaz.n11casestudy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anilyilmaz.n11casestudy.core.database.dao.FavoriteUserDao
import com.anilyilmaz.n11casestudy.core.database.dao.RemoteKeyDao
import com.anilyilmaz.n11casestudy.core.database.dao.UserDao
import com.anilyilmaz.n11casestudy.core.database.dao.UserDetailDao
import com.anilyilmaz.n11casestudy.core.database.entity.FavoriteUserEntity
import com.anilyilmaz.n11casestudy.core.database.entity.RemoteKeyEntity
import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity

@Database(
    entities = [ UserEntity::class, RemoteKeyEntity::class, FavoriteUserEntity::class,
        UserDetailEntity::class ],
    version = 1,
    exportSchema = false,
)
abstract class N11Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun favoriteUserDao(): FavoriteUserDao
    abstract fun userDetailDao(): UserDetailDao
}