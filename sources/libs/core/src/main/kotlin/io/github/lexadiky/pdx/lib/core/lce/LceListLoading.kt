package io.github.lexadiky.pdx.lib.core.lce

import arrow.core.Either
import io.github.lexadiky.pdx.lib.core.collection.replaced
import io.github.lexadiky.pdx.lib.core.utils.asLce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun <F, T, E> lceFlow(
    input: List<F>,
    mapper: suspend (F) -> Either<E, T>,
): Flow<List<Lce<E, T>>> {
    var readyBuffer: List<Lce<E, T>> = List(input.size) { Lce.Loading }
    val mutex = Mutex()

    return channelFlow {
        send(readyBuffer)

        input.forEachIndexed { index, item ->
            launch(Dispatchers.IO) {
                val newItem = mapper(item).asLce()
                mutex.withLock {
                    readyBuffer = readyBuffer.replaced(index, newItem)
                    send(readyBuffer)
                }
            }
        }
    }
}

private fun <T> flowOfIterable(items: Iterable<T>): Flow<T> = flow {
    items.forEach { emit(it) }
}
