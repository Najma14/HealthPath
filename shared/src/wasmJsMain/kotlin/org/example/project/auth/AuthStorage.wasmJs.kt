package org.example.project.auth

import kotlinx.browser.localStorage

private const val KEY_LOGGED_IN = "healthpath_logged_in"
private const val KEY_EMAIL = "healthpath_email"
private const val KEY_PASSWORD = "healthpath_password"

private class WasmAuthStorage : AuthStorage {

    override fun hasSession(): Boolean {
        val logged = localStorage.getItem(KEY_LOGGED_IN) == "true"
        val email = localStorage.getItem(KEY_EMAIL) ?: ""
        return logged && email.isNotBlank()
    }

    override fun saveSession(email: String, password: String) {
        localStorage.setItem(KEY_LOGGED_IN, "true")
        localStorage.setItem(KEY_EMAIL, email.trim())
        localStorage.setItem(KEY_PASSWORD, password)
    }

    override fun clear() {
        localStorage.removeItem(KEY_LOGGED_IN)
        localStorage.removeItem(KEY_EMAIL)
        localStorage.removeItem(KEY_PASSWORD)
    }
}

actual fun createAuthStorage(): AuthStorage = WasmAuthStorage()
