package com.hamric.achievement.data.repository

import com.hamric.achievement.data.api.AchievementApi
import com.hamric.achievement.domain.model.Achievement
import com.hamric.achievement.domain.repository.AchievementRepository
import javax.inject.Inject

class AchievementRepositoryImpl @Inject constructor(
    private val api: AchievementApi
) : AchievementRepository {

    override suspend fun fetchAchievements(): Result<List<Achievement>> {
        return try {
            val response = api.getAchievements()
            val achievements = response.data.map { dto ->
                Achievement(
                    label = dto.label,
                    type = dto.type,
                    minimumTarget = dto.minimumTarget.toUInt(),
                    currentTarget = dto.currentValue.toUInt(),
                    target = dto.target.toUInt()
                )
            }
            Result.success(achievements)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}