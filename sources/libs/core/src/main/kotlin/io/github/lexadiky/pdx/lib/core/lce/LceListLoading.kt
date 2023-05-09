package io.github.lexadiky.pdx.lib.core.lce

import arrow.core.Either
import io.github.lexadiky.pdx.lib.core.collection.replaced
import io.github.lexadiky.pdx.lib.core.utils.asLce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun <F, T, E> lceFlow(
    input: List<F>,
    mapper: suspend (F) -> Either<E, T>,
): DynamicLceList<E, T> {
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
    }.distinctUntilChanged()
}
