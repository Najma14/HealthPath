package org.example.project.auth

import platform.Foundation.NSUserDefaults

private const val KEY_LOGGED_IN = "healthpath_logged_in"
private const val KEY_EMAIL = "healthpath_email"
private const val KEY_PASSWORD = "healthpath_password"

private class IosAuthStorage : AuthStorage {

    private val defaults = NSUserDefaults.standardUserDefaults

    override fun hasSession(): Boolean =
        defaults.boolForKey(KEY_LOGGED_IN) &&
            defaults.stringForKey(KEY_EMAIL).orEmpty().isNotBlank()

    override fun saveSession(email: String, password: String) {
        defaults.setBool(true, forKey = KEY_LOGGED_IN)
        defaults.setObject(email.trim(), forKey = KEY_EMAIL)
        defaults.setObject(password, forKey = KEY_PASSWORD)
    }

    override fun clear() {
        defaults.removeObjectForKey(KEY_LOGGED_IN)
        defaults.removeObjectForKey(KEY_EMAIL)
        defaults.removeObjectForKey(KEY_PASSWORD)
    }
}

actual fun createAuthStorage(): AuthStorage = IosAuthStorage()
