package com.anilyilmaz.n11casestudy.core.data.repository

import com.anilyilmaz.n11casestudy.core.database.entity.UserDetailEntity
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun getUserDetail(login: String) : Flow<UserDetailEntity?>
}