package io.github.lexadiky.pdx.lib.resources.image

object PlaceholderImageResource : ImageResource

fun ImageResource.Companion.placeholder(): ImageResource = PlaceholderImageResource

fun ImageResource?.orPlaceholder(): ImageResource = this ?: PlaceholderImageResource
