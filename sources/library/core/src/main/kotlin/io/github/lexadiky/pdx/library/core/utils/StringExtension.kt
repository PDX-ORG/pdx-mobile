package io.github.lexadiky.pdx.library.core.utils

/**
 * Removes all new line symbols from [this].
 */
fun String.removeNewLines(): String = replace("\n", " ")
