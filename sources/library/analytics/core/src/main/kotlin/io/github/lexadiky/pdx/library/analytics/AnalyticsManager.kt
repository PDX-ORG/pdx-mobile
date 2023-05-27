package io.github.lexadiky.pdx.library.analytics

import io.github.lexadiky.pdx.library.analytics.sender.EventSender

interface AnalyticsManager : EventSender {

    companion object {

        fun delegate(eventSenders: List<EventSender>): AnalyticsManager {
            return DelegatingAnalyticsManager(eventSenders)
        }
    }
}

