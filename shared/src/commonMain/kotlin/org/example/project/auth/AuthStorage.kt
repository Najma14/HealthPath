package org.example.project.auth

/** Persists email/password until [clear] (logout) or the user clears app data. */
interface AuthStorage {
    fun hasSession(): Boolean
    fun saveSession(email: String, password: String)
    fun clear()
}

expect fun createAuthStorage(): AuthStorage
