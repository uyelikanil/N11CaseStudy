package com.anilyilmaz.n11casestudy.core.data.repository

import com.anilyilmaz.n11casestudy.core.common.Dispatcher
import com.anilyilmaz.n11casestudy.core.common.N11Dispatchers
import com.anilyilmaz.n11casestudy.core.database.N11Database
import com.anilyilmaz.n11casestudy.core.database.entity.FavoriteUserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteUserRepositoryImpl @Inject constructor(
    private val n11Database: N11Database,
    @Dispatcher(N11Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): FavoriteUserRepository {
    override suspend fun insertToFavorites(favoriteUserEntity: FavoriteUserEntity) =
        withContext(ioDispatcher) {
        n11Database.favoriteUserDao().insertToFavorites(favoriteUserEntity)
    }

    override fun favoriteUsers(): Flow<List<FavoriteUserEntity>> =
        n11Database.favoriteUserDao().favoriteUsers()

    override suspend fun deleteFromFavorites(userId: Long) = withContext(ioDispatcher) {
        n11Database.favoriteUserDao().deleteFromFavorites(userId)
    }
}