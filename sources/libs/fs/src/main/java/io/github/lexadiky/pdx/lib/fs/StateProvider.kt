package io.github.lexadiky.pdx.lib.fs

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StateProvider(private val sp: SharedPreferences) {

    fun int(key: String, default: Int) = IntFsState(key, sp, default)
}

class IntFsState(
    private val key: String,
    private val sp: SharedPreferences,
    private val default: Int
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return sp.getInt(key, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        sp.edit()
            .putInt(key, value)
            .apply()
    }
}