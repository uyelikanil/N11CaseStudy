package com.anilyilmaz.n11casestudy.core.data.di

import com.anilyilmaz.n11casestudy.core.data.repository.FavoriteUserRepository
import com.anilyilmaz.n11casestudy.core.data.repository.FavoriteUserRepositoryImpl
import com.anilyilmaz.n11casestudy.core.data.repository.UserDetailRepository
import com.anilyilmaz.n11casestudy.core.data.repository.UserDetailRepositoryImpl
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
    @Binds
    fun bindsFavoriteUserRepository(favoriteUserRepository: FavoriteUserRepositoryImpl
    ): FavoriteUserRepository
    @Binds
    fun bindsUserDetailRepository(userDetailRepository: UserDetailRepositoryImpl
    ): UserDetailRepository
}