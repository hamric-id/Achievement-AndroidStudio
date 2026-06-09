package com.hamric.achievement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamric.achievement.domain.usecase.FetchAchievementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val fetchAchievementsUseCase: FetchAchievementsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AchievementState())
    val state: StateFlow<AchievementState> = _state.asStateFlow()

    fun handleIntent(intent: AchievementIntent) {
        when (intent) {
            is AchievementIntent.LoadAchievements -> loadAchievements()
            is AchievementIntent.UpdateSearchText -> updateSearchText(intent.text)
            is AchievementIntent.OnDetailTap -> onDetailTap(intent.type)
        }
    }

    private fun loadAchievements() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = fetchAchievementsUseCase()

            result.onSuccess { achievements ->
                _state.update { it.copy(
                    isLoading = false,
                    items = achievements
                ) }
            }.onFailure { error ->
                _state.update { it.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Unknown error occurred"
                ) }
            }
        }
    }

    private fun updateSearchText(text: String) {
        _state.update { it.copy(searchText = text) }
    }

    private fun onDetailTap(type: String) {
        // Handle navigation or analytics here
        println("Detail tapped for: $type")
    }
}