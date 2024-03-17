package com.anilyilmaz.n11casestudy.core.common.di

import com.anilyilmaz.n11casestudy.core.common.Dispatcher
import com.anilyilmaz.n11casestudy.core.common.N11Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(N11Dispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}