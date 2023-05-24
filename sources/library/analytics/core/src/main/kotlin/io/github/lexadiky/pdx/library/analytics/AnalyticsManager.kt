package io.github.lexadiky.pdx.library.analytics

import io.github.lexadiky.pdx.library.analytics.sender.EventSender

class AnalyticsManager : EventSender {

    private val eventSenders: MutableList<EventSender> = ArrayList()

    override fun log(event: String, parameters: Map<String, Any>) {
        eventSenders.forEach { sender -> sender.log(event, parameters) }
    }

    fun registerEventSender(sender: EventSender) {
        eventSenders += sender
    }
}
