package io.github.lexadiky.pdx.lib.fs

import android.content.Context
import io.github.lexadiky.pdx.lib.fs.statist.RoboStaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

class RoboFsManager(
    private val resourceProvider: RoboStaticResourceProvider
) : FsManager {

    override fun static(): StaticResourceProvider = resourceProvider

    override suspend fun drop() {

    }

    companion object {

        private const val KEY_REGISTER_STATES = "register_states"
    }
}
