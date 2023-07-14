package io.github.lexadiky.pdx.library.microdata

import kotlin.reflect.KType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class SerialEditableMicrodata<T : Any>(
    private val base: EditableMicrodata<String>,
    type: KType,
    private val json: Json,
) : EditableMicrodata<T> {

    private val serializer = json.serializersModule.serializer(type)

    override suspend fun set(value: T) {
        base.set(
            json.encodeToString(
                serializer = serializer,
                value = value
            )
        )
    }

    override suspend fun delete() {
        base.delete()
    }

    @Suppress("UNCHECKED_CAST")
    override fun observe(): Flow<T?> {
        return base.observe().map {
            val baseValue = base.get() ?: return@map null
            json.decodeFromString(serializer, baseValue) as T?
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(): T? {
        val baseValue = base.get() ?: return null
        return json.decodeFromString(serializer, baseValue) as T?
    }
}