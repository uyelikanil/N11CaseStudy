package com.anilyilmaz.n11casestudy.core.domain.usecase

import com.anilyilmaz.n11casestudy.core.data.repository.FavoriteUserRepository
import com.anilyilmaz.n11casestudy.core.data.repository.UserDetailRepository
import com.anilyilmaz.n11casestudy.core.domain.mapper.toUserDetail
import com.anilyilmaz.n11casestudy.core.model.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
    private val favoriteUserRepository: FavoriteUserRepository
) {
    operator fun invoke(username: String): Flow<UserDetail?> =
        userDetailRepository.getUserDetail(username)
            .combine(favoriteUserRepository.favoriteUsers()) { userDetail, favoriteUsers ->
                val isUserFavorite = favoriteUsers.any {  it.userId == userDetail?.id }
                userDetail?.toUserDetail(isUserFavorite)
            }
}