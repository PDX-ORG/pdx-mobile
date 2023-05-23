package io.github.lexadiky.pdx.lib.target

import android.content.Context
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.analytics.AnalyticsManager
import io.github.lexadiky.pdx.library.analytics.sender.BloggerEventSender
import io.github.lexadiky.pdx.library.analytics.sender.FirebaseEventSender

val AnalyticsModule by module("analytics") {
    single { createAnalyticsManager(inject()) }
}

private fun createAnalyticsManager(context: Context): AnalyticsManager {
    return AnalyticsManager().apply {
        registerEventSender(FirebaseEventSender.create(context))
        registerEventSender(BloggerEventSender())
    }
}
