package io.github.lexadiky.pdx.library.microdata

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import java.util.WeakHashMap
import kotlinx.coroutines.CoroutineScope

class MicrodataFactory(
    private val ds: DataStore<Preferences>,
    private val scope: CoroutineScope
) {

    private val cache: MutableMap<Preferences.Key<*>, EditableMicrodata<*>> = WeakHashMap()

    fun integer(key: String): EditableMicrodata<Int> = cached(intPreferencesKey(key))

    fun string(key: String): EditableMicrodata<String> = cached(stringPreferencesKey(key))

    fun boolean(key: String): EditableMicrodata<Boolean> = cached(booleanPreferencesKey(key))

    fun strings(key: String): EditableMicrodata<Set<String>> = cached(stringSetPreferencesKey(key))

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> cached(key: Preferences.Key<T>): EditableMicrodata<T> =
        cache.computeIfAbsent(key) { prefKey ->
            EditableMicrodata(
                ds = ds,
                key = prefKey as Preferences.Key<T>,
                scope = scope
            )
        } as EditableMicrodata<T>
}

