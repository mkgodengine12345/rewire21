package com.rewire21.app.data.repository

import android.content.Context
import com.rewire21.app.data.database.RewireDatabase
import com.rewire21.app.data.database.dao.UserDao
import com.rewire21.app.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RewireRepository @Inject constructor(
    private val context: Context
) {
    private val database = RewireDatabase.getInstance(context)
    private val userDao: UserDao = database.userDao()

    suspend fun getUser(): UserEntity = userDao.getUser()
    fun getUserFlow(): Flow<UserEntity> = userDao.getUserFlow()

    suspend fun addPoints(amount: Int) = userDao.addPoints(amount)
    suspend fun deductPoints(amount: Int) = userDao.deductPoints(amount)
    suspend fun setPoints(points: Int) = userDao.setPoints(points)

    suspend fun incrementStreak() = userDao.incrementStreak()
    suspend fun resetStreak() = userDao.resetStreak()

    suspend fun setVaultLocked(locked: Boolean) = userDao.setVaultLocked(locked)
    suspend fun updateDailyLimit(hours: Int) = userDao.updateDailyLimit(hours)
    suspend fun setOnboardingComplete(completed: Boolean) = userDao.setOnboardingComplete(completed)

    suspend fun saveUser(user: UserEntity) = userDao.insertUser(user)

    suspend fun initializeUser() {
        try {
            getUser()
        } catch (e: Exception) {
            val defaultUser = UserEntity()
            userDao.insertUser(defaultUser)
        }
    }
}
