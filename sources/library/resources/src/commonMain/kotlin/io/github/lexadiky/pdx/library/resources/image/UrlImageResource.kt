package io.github.lexadiky.pdx.library.resources.image

class UrlImageResource internal constructor(val url: String) : ImageResource

fun ImageResource.Companion.from(url: String?): ImageResource {
    return url?.normalizeUrl()?.let(::UrlImageResource)
        ?: ImageResource.placeholder()
}

/**
 * Normalizes url removing HTML special characters
 */
private fun String.normalizeUrl(): String = replace("&amp;", "&")
