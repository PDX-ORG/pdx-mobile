package io.github.lexadiky.pdx.lib.target.util

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LoggerLevel

private class CrashlyticsLoggerDelegate : LoggerDelegate {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        crashlytics.log("$level/$tag: $message")
        if (throwable != null && level == LoggerLevel.ERROR) {
            crashlytics.recordException(throwable)
        }
    }
}

val LoggerConfigurator.crashlytics: LoggerDelegate get() = CrashlyticsLoggerDelegate()