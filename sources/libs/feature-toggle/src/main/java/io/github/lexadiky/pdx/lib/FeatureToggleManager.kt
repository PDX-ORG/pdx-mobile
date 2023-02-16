@file:OptIn(ExperimentalTime::class)

package io.github.lexadiky.pdx.lib

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.info
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.resume
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.tasks.await

class FeatureToggleManager(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) {
    suspend fun sync() {
        withTimeout(SYNC_TIMEOUT) {
            syncInternal()
            BLogger.tag("FeatureToggleManager").info("successfully synced firebase remote config")
        }
    }

    fun resolve(toggle: BooleanFeatureToggle): Boolean {
        val configKey = formatFeatureToggleFbKey(toggle)
        return try {
            firebaseRemoteConfig.all.getOrDefault(configKey, null)
                ?.asBoolean()
                ?: toggle.defaultValue
        } catch (e: Throwable) {
            BLogger.tag("FeatureToggleManager")
                .error("can't resolve toggle $configKey", e)
            toggle.defaultValue
        }
    }

    private fun formatFeatureToggleFbKey(toggle: FeatureToggle<*>): String {
        return "ft_${toggle.group}_${toggle.id}"
    }

    private suspend fun syncInternal() {
        firebaseRemoteConfig.fetchAndActivate().await()
    }

    companion object {

        private val SYNC_TIMEOUT = 10.seconds
    }
}

inline fun FeatureToggleManager.ifEnabled(toggle: BooleanFeatureToggle, callback: () -> Unit) {
    if (resolve(toggle)) {
        callback()
    }
}
