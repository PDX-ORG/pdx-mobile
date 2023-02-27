package io.github.lexadiky.pdx.lib.resources.string

class LiteralStringResource(val value: String) : StringResource

fun StringResource.Companion.from(text: String): StringResource {
    return LiteralStringResource(text)
}

fun StringResource.Companion.empty(): StringResource {
    return LiteralStringResource("")
}
