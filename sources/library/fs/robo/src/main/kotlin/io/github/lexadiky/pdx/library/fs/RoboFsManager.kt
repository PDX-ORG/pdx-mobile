package io.github.lexadiky.pdx.library.fs

import io.github.lexadiky.pdx.library.fs.statist.RoboStaticResourceProvider
import io.github.lexadiky.pdx.library.fs.statist.StaticResourceProvider

class RoboFsManager(
    private val resourceProvider: RoboStaticResourceProvider
) : FsManager {

    override fun static(): StaticResourceProvider = resourceProvider

    override suspend fun drop() {
        // TODO not implemented
    }
}
