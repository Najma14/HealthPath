package org.example.project.auth

import android.content.Context

object AuthStorageAndroidContext {
    private var application: Context? = null

    fun init(context: Context) {
        application = context.applicationContext
    }

    fun require(): Context =
        checkNotNull(application) {
            "AuthStorageAndroidContext.init must be called before Compose (e.g. in Application.onCreate or Activity.onCreate)."
        }
}
