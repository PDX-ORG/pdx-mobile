package io.github.lexadiky.pdx.library.target.util

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.lexadiky.akore.blogger.LogLevel
import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate

private class CrashlyticsLoggerDelegate : LoggerDelegate {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        crashlytics.log("$level/$tag: $message")
        if (throwable != null && level == LogLevel.ERROR) {
            crashlytics.recordException(throwable)
        }
    }
}

val LoggerConfigurator.crashlytics: LoggerDelegate get() = CrashlyticsLoggerDelegate()
