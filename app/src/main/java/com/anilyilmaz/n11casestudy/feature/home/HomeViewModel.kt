package com.anilyilmaz.n11casestudy.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.anilyilmaz.n11casestudy.core.data.repository.FavoriteUserRepository
import com.anilyilmaz.n11casestudy.core.domain.usecase.SearchUserUseCase
import com.anilyilmaz.n11casestudy.core.domain.usecase.UpdateFavoriteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val favoriteUserRepository: FavoriteUserRepository,
    private val searchUserUseCase: SearchUserUseCase,
    private val updateFavoriteUserUseCase: UpdateFavoriteUserUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResultUiState = searchQuery.flatMapLatest { query ->
        delay(1000L)
        searchUserUseCase(query)
            .cachedIn(viewModelScope)
            .combine(favoriteUserRepository.favoriteUsers()) { users, favoriteUsers ->
                users.map { user ->
                    val isUserFavorite = favoriteUsers.any { it.userId == user.id }
                    user.copy(isFavorite =  isUserFavorite)
                }
            }
    }.cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onFavoriteClicked(userId: Long) = viewModelScope.launch {
        updateFavoriteUserUseCase(userId = userId)
    }
}

private const val SEARCH_QUERY = "searchQuery"
