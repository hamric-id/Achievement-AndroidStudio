package com.hamric.achievement.data.dto

import com.google.gson.annotations.SerializedName

data class APIResponseDTO(
    val data: List<DataItemDTO>
)

data class DataItemDTO(
    val label: String,
    val type: String,
    val target: Int,
    @SerializedName("currentValue")
    val currentValue: Int,
    val minimumTarget: Int
)