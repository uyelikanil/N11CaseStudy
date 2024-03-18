package com.anilyilmaz.n11casestudy.feature.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.n11casestudy.core.domain.usecase.GetUserDetailUseCase
import com.anilyilmaz.n11casestudy.core.domain.usecase.UpdateFavoriteUserUseCase
import com.anilyilmaz.n11casestudy.feature.userdetail.navigation.USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    private val updateFavoriteUserUseCase: UpdateFavoriteUserUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val username: String = checkNotNull(savedStateHandle[USER_NAME])

    val uiState: StateFlow<UserDetailUiState> =
        getUserDetailUseCase(username).map { userDetail ->
            if(userDetail != null) {
                UserDetailUiState.Success(userDetail)
            } else {
                UserDetailUiState.Error
            }
        }.catch { e ->
           e.printStackTrace()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserDetailUiState.Loading
        )

    fun onFavoriteClicked(userId: Long) = viewModelScope.launch {
        updateFavoriteUserUseCase(userId = userId)
    }
}