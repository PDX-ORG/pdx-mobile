package io.github.lexadiky.pdx.library.analytics.sender

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error

class FirebaseEventSender internal constructor(private val firebaseAnalytics: FirebaseAnalytics) : EventSender {

    override fun log(event: String, parameters: Map<String, Any>) {
        firebaseAnalytics.logEvent(event, parameters.toBundle())
    }

    private fun Map<String, Any>.toBundle(): Bundle {
        val bundle = Bundle()
        entries.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                is Double -> bundle.putDouble(key, value)
                is Float -> bundle.putFloat(key, value)
                is Byte -> bundle.putByte(key, value)
                is Short -> bundle.putShort(key, value)
                is Long -> bundle.putLong(key, value)
            }
        }

        return bundle
    }

    companion object {

        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WAKE_LOCK
        )

        @SuppressLint("MissingPermission")
        @Suppress("SwallowedException")
        fun create(context: Context): EventSender {
            val allPermissionsGranted = REQUIRED_PERMISSIONS.all {
                context.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            }

            if (!allPermissionsGranted) {
                BLogger.tag("FirebaseEventSender")
                    .error(
                        "not all required permissions granted:\n" +
                                REQUIRED_PERMISSIONS.map { permission ->
                                    "$permission => ${
                                        if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                                            "granted"
                                        } else {
                                            "not denied"
                                        }

                                    }"
                                }.joinToString(separator = "\n")
                    )
            }

            return try {
                FirebaseEventSender(FirebaseAnalytics.getInstance(context))
            } catch (e: Throwable) {
                NoOpEventSender()
            }
        }
    }
}
