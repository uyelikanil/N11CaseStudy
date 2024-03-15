package com.anilyilmaz.n11casestudy.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.n11casestudy.core.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserSearchRepository {
    fun searchUsers(query: String) : Flow<PagingData<UserEntity>>
}