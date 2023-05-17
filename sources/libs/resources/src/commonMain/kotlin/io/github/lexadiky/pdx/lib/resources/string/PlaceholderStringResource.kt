package io.github.lexadiky.pdx.lib.resources.string

object PlaceholderStringResource : StringResource

fun StringResource.Companion.placeholder(): StringResource = PlaceholderStringResource

fun StringResource?.orPlaceholder(): StringResource = this ?: StringResource.placeholder()
