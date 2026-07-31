package com.rewire21.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rewire21.app.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = '1'")
    suspend fun getUser(): UserEntity

    @Query("SELECT * FROM users WHERE id = '1'")
    fun getUserFlow(): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE users SET points = points + :amount")
    suspend fun addPoints(amount: Int)

    @Query("UPDATE users SET points = points - :amount")
    suspend fun deductPoints(amount: Int)

    @Query("UPDATE users SET points = :points")
    suspend fun setPoints(points: Int)

    @Query("UPDATE users SET streakDays = streakDays + 1")
    suspend fun incrementStreak()

    @Query("UPDATE users SET streakDays = 0")
    suspend fun resetStreak()

    @Query("UPDATE users SET isVaultLocked = :locked")
    suspend fun setVaultLocked(locked: Boolean)

    @Query("UPDATE users SET dailyLimitHours = :hours")
    suspend fun updateDailyLimit(hours: Int)

    @Query("UPDATE users SET hasCompletedOnboarding = :completed")
    suspend fun setOnboardingComplete(completed: Boolean)
}
