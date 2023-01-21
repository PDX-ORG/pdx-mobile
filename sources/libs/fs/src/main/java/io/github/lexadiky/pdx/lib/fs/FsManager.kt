package io.github.lexadiky.pdx.lib.fs

import android.content.Context

class FsManager(private val context: Context) {

    private val managementSharedPreferences = context.getSharedPreferences("management.fs.local", Context.MODE_PRIVATE)

    fun state(group: String): StateProvider {
        val groupName = "$group.fs.local"
        registerGroup(groupName)
        return StateProvider(
            context.getSharedPreferences(groupName, Context.MODE_PRIVATE)
        )
    }


    fun drop() {
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