package com.anilyilmaz.n11casestudy.core.network.di

import com.anilyilmaz.n11casestudy.core.network.datasource.GithubDataSource
import com.anilyilmaz.n11casestudy.core.network.datasource.GithubDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindsGithubDataSource(githubDataSource: GithubDataSourceImpl): GithubDataSource
}