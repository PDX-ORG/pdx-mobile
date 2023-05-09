package io.github.lexadiky.pdx.lib.core.collection

/**
 * Equivalent of setting list's slot value by index. But instead of mutation returns new list.
 */
fun <T> List<T>.replaced(index: Int, value: T): List<T> {
    val copy = this.toMutableList()
    copy[index] = value
    return copy
}
