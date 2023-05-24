package io.github.lexadiky.pdx.library.analytics.sender

class NoOpEventSender : EventSender {

    override fun log(event: String, parameters: Map<String, Any>) = Unit
}
