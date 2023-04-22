package io.github.lexadiky.pdx.lib.core.lce

import arrow.core.Either
import io.github.lexadiky.pdx.lib.core.collection.replaced
import io.github.lexadiky.pdx.lib.core.utils.asLce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun <F, T, E> lceFlow(input: List<F>, mapper: suspend (F) -> Either<E, T>): Flow<List<Lce<E, T>>> {
    var readyBuffer: List<Lce<E, T>> = List(input.size) { Lce.Loading }
    val mutex = Mutex()

    return flow {
        emit(readyBuffer)
        flowOfIterable(input.withIndex())
            .flowOn(Dispatchers.IO)
            .map { (index, item) -> index to mapper(item) }
            .collect { (index, item) ->
                mutex.withLock {
                    readyBuffer = readyBuffer.replaced(index, item.asLce())
                    emit(readyBuffer)
                }
            }
    }
}

private fun <T> flowOfIterable(items: Iterable<T>): Flow<T> = flow {
    items.forEach { emit(it) }
}
