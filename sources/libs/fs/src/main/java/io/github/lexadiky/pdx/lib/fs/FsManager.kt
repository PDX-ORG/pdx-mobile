package io.github.lexadiky.pdx.lib.fs

import android.content.Context

class FsManager(private val context: Context) {

    fun state(group: String): StateProvider {
        return StateProvider(
            context.getSharedPreferences("$group.fs.local", Context.MODE_PRIVATE)
        )
    }
}