package com.anilyilmaz.n11casestudy.core.database.di

import android.content.Context
import androidx.room.Room
import com.anilyilmaz.n11casestudy.core.database.N11Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesN11Database(
        @ApplicationContext context: Context,
    ): N11Database = Room.databaseBuilder(
        context,
        N11Database::class.java,
        "n11-database",
    ).build()
}