package com.hamric.achievement.domain.repository


import com.hamric.achievement.domain.model.Achievement

interface AchievementRepository {
    suspend fun fetchAchievements(): Result<List<Achievement>>
}