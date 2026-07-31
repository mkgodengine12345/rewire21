package com.rewire21.app.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.GeneralSecurityException

object EncryptionUtil {

    private const val PREFS_NAME = "rewire_secure_prefs"
    private const val MASTER_KEY_ALIAS = "rewire_master_key"

    @Throws(GeneralSecurityException::class)
    fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context, MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Throws(GeneralSecurityException::class)
    fun getSecurePreferences(context: Context): EncryptedSharedPreferences {
        val masterKey = getMasterKey(context)
        return EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    object Keys {
        const val POINTS = "points"
        const val DAILY_LIMIT = "daily_limit"
        const val STREAK = "streak"
        const val VAULT_LOCKED = "vault_locked"
    }
}
