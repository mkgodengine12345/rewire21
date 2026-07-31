package com.rewire21.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rewire21.app.data.repository.RewireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: RewireRepository
) : ViewModel() {

    val user = repository.getUserFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        viewModelScope.launch {
            repository.initializeUser()
        }
    }

    fun updateDailyLimit(hours: Int) {
        viewModelScope.launch {
            repository.updateDailyLimit(hours)
        }
    }

    fun setOnboardingComplete() {
        viewModelScope.launch {
            repository.setOnboardingComplete(true)
        }
    }

    fun resetVault() {
        viewModelScope.launch {
            repository.setVaultLocked(false)
            val user = repository.getUser()
            val points = when (user.dailyLimitHours) {
                1 -> 2000
                2 -> 4000
                3 -> 5000
                4 -> 7000
                5 -> 8500
                else -> 10000
            }
            repository.setPoints(points)
        }
    }
}
