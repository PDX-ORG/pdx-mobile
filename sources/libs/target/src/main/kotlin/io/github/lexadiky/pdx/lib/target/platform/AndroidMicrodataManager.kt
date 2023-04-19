package io.github.lexadiky.pdx.lib.target.platform

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import io.github.lexadiky.pdx.lib.microdata.Microdata
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import java.util.concurrent.Executors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher

@Suppress("PrivatePropertyName")
private val STATIC_PREFERENCES_DATASTORE_CACHE = HashMap<String, DataStore<Preferences>>()

class AndroidMicrodataManager(private val context: Context) : MicrodataManager {

    private val scope = CoroutineScope(
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    )
    private val dummyDs: DataStore<Preferences>? = null

    override fun acquire(owner: Any, database: String): Microdata {
        val datastore = STATIC_PREFERENCES_DATASTORE_CACHE.getOrPut(database) {
            preferencesDataStore(database)
                .getValue(context, ::dummyDs)
        }
        return Microdata(datastore, scope)
    }
}
