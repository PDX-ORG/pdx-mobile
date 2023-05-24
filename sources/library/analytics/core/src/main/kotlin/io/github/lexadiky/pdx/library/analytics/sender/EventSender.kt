package io.github.lexadiky.pdx.library.analytics.sender

interface EventSender {

    fun log(event: String, parameters: Map<String, Any>)
}
