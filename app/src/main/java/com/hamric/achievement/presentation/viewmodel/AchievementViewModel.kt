package com.hamric.achievement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor() : ViewModel() {

    // MVI State Flow
    private val _state = MutableStateFlow(AchievementState())
    val state: StateFlow<AchievementState> = _state.asStateFlow()

    // Handle user intents
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

            //fetch achievement use case
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