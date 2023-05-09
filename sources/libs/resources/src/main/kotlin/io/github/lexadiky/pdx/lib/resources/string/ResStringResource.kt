package io.github.lexadiky.pdx.lib.resources.string

class ResStringResource internal constructor(val stringRes: Int) : StringResource

fun StringResource.Companion.from(res: Int): StringResource {
    return ResStringResource(res)
}
