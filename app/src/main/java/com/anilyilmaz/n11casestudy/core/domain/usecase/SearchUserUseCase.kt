package com.anilyilmaz.n11casestudy.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.anilyilmaz.n11casestudy.core.data.repository.UserSearchRepository
import com.anilyilmaz.n11casestudy.core.domain.mapper.toUser
import com.anilyilmaz.n11casestudy.core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val userSearchRepository: UserSearchRepository
) {
    operator fun invoke(query: String): Flow<PagingData<User>> =
        userSearchRepository.searchUsers(query).map { pagingData ->
            pagingData.map { userEntity ->
                userEntity.toUser()
            }
        }
}