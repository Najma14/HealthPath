package org.example.project.auth

import android.content.Context

private const val PREFS = "healthpath_auth"
private const val KEY_LOGGED_IN = "logged_in"
private const val KEY_EMAIL = "email"
private const val KEY_PASSWORD = "password"

internal class AndroidAuthStorage(
    private val context: Context,
) : AuthStorage {

    private val prefs by lazy {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    override fun hasSession(): Boolean =
        prefs.getBoolean(KEY_LOGGED_IN, false) &&
            prefs.getString(KEY_EMAIL, null).orEmpty().isNotBlank()

    override fun saveSession(email: String, password: String) {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_EMAIL, email.trim())
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}

actual fun createAuthStorage(): AuthStorage = AndroidAuthStorage(AuthStorageAndroidContext.require())
