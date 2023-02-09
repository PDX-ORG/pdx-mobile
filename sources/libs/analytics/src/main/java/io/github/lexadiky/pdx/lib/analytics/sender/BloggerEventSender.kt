package io.github.lexadiky.pdx.lib.analytics.sender

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info

internal class BloggerEventSender : EventSender {

    override fun log(event: String, parameters: Map<String, Any>) {
        BLogger.tag("AnalyticsEvent")
            .info("$event: ${parameters.entries.joinToString(separator = ", ")}")
    }
}
