package io.github.lexadiky.pdx.lib.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await

class FirebaseRemoteConfigProvider(
    private val application: FirebaseApp
) {
    private val instance: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance(application)
    }

    suspend fun get(): FirebaseRemoteConfig {
        sync()
        return instance
    }

    suspend fun sync() {
        instance.fetchAndActivate().await()
    }
}