package com.rewire21.app.service.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import com.rewire21.app.data.repository.RewireRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class RewireAccessibilityService : AccessibilityService() {

    @Inject lateinit var repository: RewireRepository

    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var currentPackage: String? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        serviceInfo = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event ?: return

        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return

            // Skip system and banking apps
            if (isExcludedPackage(packageName)) return

            if (currentPackage != packageName) {
                currentPackage = packageName
                serviceScope.launch {
                    checkLockState(packageName)
                }
            }
        }
    }

    private suspend fun checkLockState(packageName: String) {
        val user = repository.getUser()
        if (user.isVaultLocked) {
            // Show lock screen overlay
        }
    }

    private fun isExcludedPackage(packageName: String): Boolean {
        val excluded = listOf(
            "com.whatsapp",
            "com.google.android.apps.nbu.paisa.user",
            "com.phonepe.app",
            "net.one97.paytm"
        )
        return excluded.any { packageName.contains(it) }
    }

    override fun onInterrupt() {}
}
