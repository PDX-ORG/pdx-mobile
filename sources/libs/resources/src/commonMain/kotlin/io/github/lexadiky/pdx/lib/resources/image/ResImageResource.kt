package io.github.lexadiky.pdx.lib.resources.image

class ResImageResource internal constructor(val id: Int) : ImageResource

fun ImageResource.Companion.from(id: Int): ImageResource = ResImageResource(id)
