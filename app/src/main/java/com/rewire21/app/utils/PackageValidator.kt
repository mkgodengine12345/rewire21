package com.rewire21.app.utils

object PackageValidator {

    private val entertainmentApps = setOf(
        "com.instagram.android",
        "com.zhiliaoapp.musically",
        "com.google.android.youtube",
        "com.facebook.katana",
        "com.snapchat.android",
        "com.twitter.android"
    )

    private val excludedApps = setOf(
        "com.whatsapp",
        "com.google.android.apps.nbu.paisa.user",
        "com.phonepe.app",
        "net.one97.paytm",
        "com.android.chrome"
    )

    fun isEntertainmentApp(packageName: String): Boolean {
        return entertainmentApps.contains(packageName)
    }

    fun isExcludedApp(packageName: String): Boolean {
        return excludedApps.any { packageName.contains(it) }
    }

    fun shouldMonitor(packageName: String): Boolean {
        return isEntertainmentApp(packageName) && !isExcludedApp(packageName)
    }
}
