package io.github.lexadiky.akore.lechuck

import java.net.URI
import java.net.URL
import java.net.URLEncoder

@JvmInline
value class NavigationRoute private constructor(private val value: String) {

    val scheme: String?
        get() = value.split(SCHEME_SPLITTER)
            .takeIf { it.size > 1 }
            ?.firstOrNull()

    val isHttp
        get() = scheme == LINK_PREFIX_HTTP ||
                scheme == LINK_PREFIX_HTTPS

    fun asString(): String = value

    fun asURI(): URI = URLEncoder.encode(value)
        .let { URI.create(it) }

    companion object {

        private const val LINK_PREFIX_HTTPS = "https"
        private const val LINK_PREFIX_HTTP = "http"
        private const val SCHEME_SPLITTER = "://"

        fun from(string: String) = NavigationRoute(string)


        fun from(url: URL) = NavigationRoute(url.toString())

        fun from(uri: URI) = NavigationRoute(uri.toString())
    }
}
