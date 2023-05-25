package io.github.lexadiky.pdx.library.fs

import io.github.lexadiky.pdx.library.fs.statist.StaticResourceProvider

interface FsManager {

    fun static(): StaticResourceProvider

    suspend fun drop()
}
