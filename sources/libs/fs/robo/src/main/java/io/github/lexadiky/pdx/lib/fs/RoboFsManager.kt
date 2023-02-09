package io.github.lexadiky.pdx.lib.fs

import android.content.Context
import io.github.lexadiky.pdx.lib.fs.atomic.AtomicStateProvider
import io.github.lexadiky.pdx.lib.fs.statist.RoboStaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider
import kotlinx.serialization.json.Json

class RoboFsManager(private val context: Context) : FsManager {

    private val managementSharedPreferences = context.getSharedPreferences("management.fs.local", Context.MODE_PRIVATE)
    private val resourceProvider = RoboStaticResourceProvider(Json { ignoreUnknownKeys = true })

    override fun atomic(groupId: String): AtomicStateProvider {
        val groupName = "$groupId.fs.local"
        registerGroup(groupName)
        return RoboStateProvider(
            context.getSharedPreferences(groupName, Context.MODE_PRIVATE)
        )
    }

    override fun static(): StaticResourceProvider = resourceProvider

    override suspend fun drop() {
        val allStates = managementSharedPreferences.getStringSet(KEY_REGISTER_STATES, emptySet())!!
        for (stateGroup in allStates) {
            context.deleteSharedPreferences(stateGroup)
        }
    }
    private fun registerGroup(name: String) {
        val newStates = managementSharedPreferences.getStringSet(KEY_REGISTER_STATES, emptySet())!! + name
        managementSharedPreferences.edit()
            .putStringSet(KEY_REGISTER_STATES, newStates)
            .apply()
    }

    companion object {

        private const val KEY_REGISTER_STATES = "register_states"
    }
}
