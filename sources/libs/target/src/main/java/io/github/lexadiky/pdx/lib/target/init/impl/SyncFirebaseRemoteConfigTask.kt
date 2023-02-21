package io.github.lexadiky.pdx.lib.target.init.impl

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.github.lexadiky.akore.alice.lookup
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.akore.blogger.warning
import io.github.lexadiky.pdx.lib.target.init.ApplicationInitializerContext
import io.github.lexadiky.pdx.lib.target.init.AsyncInitializationTask
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class SyncFirebaseRemoteConfigTask : AsyncInitializationTask {

    override val id: String = "sync-firebase"

    override val blocking: Boolean = true

    private val config = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(MIN_FETCH_INTERVAL_SECONDS)
        .build()

    override suspend fun run(context: ApplicationInitializerContext) {
        val firebaseRemoteConfig = context.di.lookup<FirebaseRemoteConfig>()

        val isSynced = withTimeoutOrNull(5.seconds) {
            performSynchorization(firebaseRemoteConfig)
            true
        } ?: false

        if (!isSynced) {
            coroutineScope {
                BLogger.tag("SyncFirebaseRemoteConfigTask")
                    .warning(
                        "can't perform firebase remote config sync in designated time, " +
                                "trying to do it in background"
                    )
                launch(Job() + Dispatchers.IO) {
                    performSynchorization(firebaseRemoteConfig)
                    BLogger.tag("SyncFirebaseRemoteConfigTask")
                        .info("fallback synchronization succeeded")
                }
            }
        }
    }

    private suspend fun performSynchorization(firebaseRemoteConfig: FirebaseRemoteConfig) {
        firebaseRemoteConfig.setConfigSettingsAsync(config).await()
        firebaseRemoteConfig.fetchAndActivate().await()
    }

    companion object {

        private const val MIN_FETCH_INTERVAL_SECONDS = 60L // 1 minute
    }
}