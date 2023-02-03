package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.pdx.lib.fs.atomic.AtomicStateProvider
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

interface FsManager {

    fun atomic(groupId: String): AtomicStateProvider

    fun static(): StaticResourceProvider

    suspend fun drop()
}