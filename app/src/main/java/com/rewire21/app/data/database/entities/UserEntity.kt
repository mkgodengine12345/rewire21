package com.rewire21.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String = "1",
    var points: Int = 3500,
    var dailyLimitHours: Int = 4,
    var streakDays: Int = 0,
    var maxStreak: Int = 0,
    var startDate: Long = System.currentTimeMillis(),
    var lastActiveDate: Long = System.currentTimeMillis(),
    var isVaultLocked: Boolean = false,
    var hasCompletedOnboarding: Boolean = false,
    var hasAgreedToTerms: Boolean = false
)
