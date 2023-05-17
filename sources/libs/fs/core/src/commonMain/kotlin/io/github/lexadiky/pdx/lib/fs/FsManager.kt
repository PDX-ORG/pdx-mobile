package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

interface FsManager {

    fun static(): StaticResourceProvider

    suspend fun drop()
}
