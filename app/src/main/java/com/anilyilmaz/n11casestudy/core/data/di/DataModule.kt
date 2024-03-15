package com.anilyilmaz.n11casestudy.core.data.di

import com.anilyilmaz.n11casestudy.core.data.repository.UserSearchRepository
import com.anilyilmaz.n11casestudy.core.data.repository.UserSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserSearchRepository(userSearchRepository: UserSearchRepositoryImpl
    ): UserSearchRepository
}