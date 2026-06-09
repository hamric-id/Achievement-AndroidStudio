package com.hamric.achievement.domain.model

import java.util.UUID

data class Achievement(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val type: String,
    val minimumTarget: UInt,
    val currentTarget: UInt,
    val target: UInt
)