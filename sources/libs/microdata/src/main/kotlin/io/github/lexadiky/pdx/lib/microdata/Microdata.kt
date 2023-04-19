package io.github.lexadiky.pdx.lib.microdata

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import java.lang.ref.Cleaner
import java.util.WeakHashMap
import java.util.concurrent.ThreadFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class Microdata(
    private val ds: DataStore<Preferences>,
    private val scope: CoroutineScope
) {

    private val cache: MutableMap<Preferences.Key<*>, EditableMicrodata<*>> = WeakHashMap()

    fun integer(key: String): EditableMicrodata<Int> = cached(intPreferencesKey(key))

    fun string(key: String): EditableMicrodata<String> = cached(stringPreferencesKey(key))

    fun strings(key: String): EditableMicrodata<Set<String>> = cached(stringSetPreferencesKey(key))

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> cached(key: Preferences.Key<T>): EditableMicrodata<T> =
        cache.computeIfAbsent(key) { prefKey ->
            EditableMicrodata(ds, scope, prefKey as Preferences.Key<T>)
        } as EditableMicrodata<T>
}

class EditableMicrodata<T : Any> internal constructor(
    internal val ds: DataStore<Preferences>,
    internal val scope: CoroutineScope,
    internal val key: Preferences.Key<T>,
) {

    private var currentValue: T? = null
    private var currentValueJob: Job? = null


    init {
        currentValueJob = scope.launch {
            ds.data.mapNotNull { it[key] }.collectLatest {
                currentValue = it
            }
        }
    }

    suspend fun set(value: T) {
        ds.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun delete() {
        ds.edit { preferences ->
            preferences.remove(key)
        }
    }

    fun observe(): Flow<T?> {
        return ds.data.mapNotNull { preferences ->
            preferences[key]
        }
    }

    fun get(): T? = currentValue


    internal fun finalize() {
        currentValueJob?.cancel()
    }
}
