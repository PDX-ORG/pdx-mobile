package io.github.lexadiky.pdx.lib.core.collection

fun <T> List<T>.replaced(index: Int, value: T): List<T> {
    val copy = this.toMutableList()
    copy[index] = value
    return copy
}