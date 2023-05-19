package io.github.lexadiky.pdx.lib.microdata

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class EditableMicrodata<T : Any> internal constructor(
    private val ds: DataStore<Preferences>,
    private val key: Preferences.Key<T>,
    scope: CoroutineScope,
) {
    private val rwl = ReentrantReadWriteLock()
    private var currentValue: T? = null
    private var currentValueJob: Job? = null


    init {
        scope.launch {
            rwl.write {
                currentValue = ds.data.first()[key]
            }
        }

        currentValueJob = scope.launch {
            ds.data.mapNotNull { it[key] }.collect {
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

    fun get(): T? = rwl.read { currentValue }


    internal fun finalize() {
        currentValueJob?.cancel()
    }
}
