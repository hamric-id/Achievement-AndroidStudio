package com.hamric.achievement.data.api


import com.hamric.achievement.data.dto.APIResponseDTO
import retrofit2.http.GET

interface AchievementApi {
    @GET("d1a07038-ae20-414c-b976-7c6bde805680")
    suspend fun getAchievements(): APIResponseDTO
}