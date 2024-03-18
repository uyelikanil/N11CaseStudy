package com.anilyilmaz.n11casestudy.feature.userdetail

import com.anilyilmaz.n11casestudy.core.model.UserDetail

sealed interface UserDetailUiState {
    data object Loading: UserDetailUiState
    data class Success(val userDetail: UserDetail) : UserDetailUiState
    data object Error: UserDetailUiState
}
