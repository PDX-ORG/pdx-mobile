@file:OptIn(ExperimentalTime::class)

package io.github.lexadiky.pdx.lib

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import kotlin.time.ExperimentalTime

class FeatureToggleManager(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) {
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
}

inline fun FeatureToggleManager.ifEnabled(toggle: BooleanFeatureToggle, callback: () -> Unit) {
    if (resolve(toggle)) {
        callback()
    }
}
