package io.github.lexadiky.pdx.lib.resources.color

class LiteralColorResource(val color: Long) : ColorResource

fun ColorResource.Companion.from(color: Long): ColorResource =
    LiteralColorResource(color)