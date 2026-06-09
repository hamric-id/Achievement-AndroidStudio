package com.hamric.achievement.presentation.viewmodel

import com.hamric.achievement.domain.model.Achievement

// MVI State (Single source of truth)
data class AchievementState(
    val isLoading: Boolean = false,
    val items: List<Achievement> = emptyList(),
    val searchText: String = "",
    val errorMessage: String? = null
) {
    val hasResults: Boolean
        get() = filteredItems.isNotEmpty()

    val emptyStateMessage: String
        get() = if (searchText.isEmpty()) "No achievements" else "No results for '$searchText'"

    val filteredItems: List<Achievement>
        get() = if (searchText.isEmpty()) {
            items
        } else {
            items.filter { item ->
                item.label.contains(searchText, ignoreCase = true) ||
                        item.type.contains(searchText, ignoreCase = true)
            }
        }
}

// MVI Intent (User actions)
sealed class AchievementIntent {
    data object LoadAchievements : AchievementIntent()
    data class UpdateSearchText(val text: String) : AchievementIntent()
    data class OnDetailTap(val type: String) : AchievementIntent()
}