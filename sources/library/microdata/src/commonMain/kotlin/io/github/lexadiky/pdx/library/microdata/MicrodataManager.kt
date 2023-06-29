package io.github.lexadiky.pdx.library.microdata

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface MicrodataManager {

    fun acquire(owner: Any, database: String): MicrodataFactory
}

fun <A: Any, B: Any, C: Any> MicrodataManager.zip(a: EditableMicrodata<A>, b: EditableMicrodata<B>, combine: (A, B) -> C): Flow<C?> {
    return a.observe().combine(b.observe()) { nextA, nextB ->
        if (nextA != null && nextB != null) {
            combine(nextA, nextB)
        } else {
            null
        }
    }
}