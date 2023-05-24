package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.pdx.lib.fs.statist.RoboStaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

class RoboFsManager(
    private val resourceProvider: RoboStaticResourceProvider
) : FsManager {

    override fun static(): StaticResourceProvider = resourceProvider

    override suspend fun drop() {
        // TODO not implemented
    }
}
