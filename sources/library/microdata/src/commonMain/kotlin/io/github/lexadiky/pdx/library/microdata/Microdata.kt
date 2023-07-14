package io.github.lexadiky.pdx.library.microdata

import kotlinx.coroutines.flow.Flow


interface Microdata<T: Any> {

    fun get(): T?

    fun observe(): Flow<T?>
}

interface EditableMicrodata<T : Any> : Microdata<T> {

    suspend fun set(value: T)
    suspend fun delete()
}
