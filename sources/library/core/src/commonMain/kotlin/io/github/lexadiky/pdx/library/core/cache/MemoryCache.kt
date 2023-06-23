package io.github.lexadiky.pdx.library.core.cache

import arrow.core.Either
import io.github.lexadiky.pdx.library.core.error.GenericError
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface MemoryCache<T: Any> {

    suspend fun update(vararg keyset: Any, fetch: suspend () -> Either<GenericError, T>): Either<GenericError, T>

    companion object {

        fun <T: Any> fresh(): MemoryCache<T> = FreshestMemoryCache()
    }
}

internal class FreshestMemoryCache<T: Any> : MemoryCache<T> {
    var keyset: Array<out Any>? = null
    var latest: Either<GenericError, T>? = null
    val mutex = Mutex()

    override suspend fun update(vararg keyset: Any, fetch: suspend () -> Either<GenericError, T>): Either<GenericError, T> {
        return mutex.withLock {
            if (this.keyset != null && latest != null && latest is Either.Right) {
                latest!!
            } else {
                this.keyset = keyset
                fetch().also { latest = it }
            }
        }
    }
}
