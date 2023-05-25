package io.github.lexadiky.pdx.feature.news.entity

import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import java.net.URI

internal data class NewsFeedItem(
    val uri: URI,
    val title: StringResource,
    val author: String,
    val authorUri: URI,
    val preview: ImageResource?,
    val time: StringResource
)
