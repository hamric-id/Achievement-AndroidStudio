package com.hamric.achievement.domain.usecase

import com.hamric.achievement.domain.model.Achievement
import com.hamric.achievement.domain.repository.AchievementRepository
import javax.inject.Inject

class FetchAchievementsUseCase @Inject constructor(
    private val repository: AchievementRepository
) {
    suspend operator fun invoke(): Result<List<Achievement>> {
        return repository.fetchAchievements()
    }
}