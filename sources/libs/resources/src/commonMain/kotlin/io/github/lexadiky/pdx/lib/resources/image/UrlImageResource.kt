package io.github.lexadiky.pdx.lib.resources.image

class UrlImageResource internal constructor(val url: String) : ImageResource

fun ImageResource.Companion.from(url: String): ImageResource {
    return UrlImageResource(url.normalizeUrl())
}

/**
 * Normalizes url removing HTML special characters
 */
private fun String.normalizeUrl(): String = replace("&amp;", "&")
