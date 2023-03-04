package io.github.lexadiky.pdx.domain.pokemon.util

private const val NEW_LINE_SYMBOL = "\n"
private const val SPACE_SYMBOL = " "

internal fun String.normalizePokeApiText() =
    replace(NEW_LINE_SYMBOL, SPACE_SYMBOL)
