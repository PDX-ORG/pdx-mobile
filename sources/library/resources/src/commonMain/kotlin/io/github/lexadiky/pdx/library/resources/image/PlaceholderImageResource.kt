package io.github.lexadiky.pdx.library.resources.image

object PlaceholderImageResource : ImageResource

fun ImageResource.Companion.placeholder(): ImageResource = PlaceholderImageResource

fun ImageResource?.orPlaceholder(): ImageResource = this ?: PlaceholderImageResource
