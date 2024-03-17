package com.anilyilmaz.n11casestudy.core.domain.usecase

import com.anilyilmaz.n11casestudy.core.data.repository.FavoriteUserRepository
import com.anilyilmaz.n11casestudy.core.database.entity.FavoriteUserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.lastOrNull
import javax.inject.Inject

class UpdateFavoriteUserUseCase @Inject constructor(
    private val favoriteUserRepository: FavoriteUserRepository
) {
    suspend operator fun invoke(userId: Long) {
        val favoriteUsers = favoriteUserRepository.favoriteUsers()
        val isUserInList = favoriteUsers.first().any { it.userId == userId }

        if(!isUserInList) {
            val favoriteUser = FavoriteUserEntity(userId = userId)
            favoriteUserRepository.insertToFavorites(favoriteUser)
        } else {
            favoriteUserRepository.deleteFromFavorites(userId = userId)
        }
    }
}