package io.github.lexadiky.pdx.library.resources.string

class FormattedStringResource internal constructor(val base: StringResource, vararg val arguments: Any) : StringResource

fun StringResource.format(vararg arguments: Any): StringResource {
    return FormattedStringResource(this, *arguments)
}
