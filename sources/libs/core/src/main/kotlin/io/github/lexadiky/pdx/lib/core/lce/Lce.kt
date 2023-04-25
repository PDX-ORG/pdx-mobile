package io.github.lexadiky.pdx.lib.core.lce

/**
 * Abstraction over Loading -> Content | Error behaviour
 *
 * Supports primitive monad operations and conversion into [Either] and [Result]
 */
sealed interface Lce<out E, out V> {

    object Loading : Lce<Nothing, Nothing>

    data class Content<out V>(val value: V) : Lce<Nothing, V>

    data class Error<out E>(val value: E): Lce<E, Nothing>
}

fun <E, T> Lce<E, T>.contentOrNull(): T? = when (this) {
    is Lce.Content -> value
    else -> null
}

@Suppress("UNCHECKED_CAST")
fun <E, V, V2> Lce<E, V>.map(transformer: (V) -> V2): Lce<E, V2> = when (this) {
    is Lce.Content -> Lce.Content(transformer(this.value))
    else -> this as Lce<E, V2>
}

fun <E, V, V2> List<Lce<E, V>>.mapLce(transformer: (V) -> V2): List<Lce<E, V2>> =
    this.map { lce: Lce<E, V> -> lce.map(transformer) }


