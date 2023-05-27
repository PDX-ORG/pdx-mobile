package io.github.lexadiky.pdx.library.analytics

import io.github.lexadiky.pdx.library.analytics.sender.EventSender

/**
 * Implementation of [AnalyticsManager] delegating sending events to [eventSenders].
 *
 * Has no additional logic attached.
 */
internal class DelegatingAnalyticsManager(
    private val eventSenders: List<EventSender>
) : AnalyticsManager {

    override fun log(event: String, parameters: Map<String, Any>) {
        eventSenders.forEach { sender -> sender.log(event, parameters) }
    }
}
