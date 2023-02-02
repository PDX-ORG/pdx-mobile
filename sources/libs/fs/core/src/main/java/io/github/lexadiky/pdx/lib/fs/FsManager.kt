package io.github.lexadiky.pdx.lib.fs

interface FsManager {

    fun atomic(groupId: String): AtomicStateProvider

    suspend fun drop()
}