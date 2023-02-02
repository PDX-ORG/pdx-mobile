package io.github.lexadiky.pdx.lib.fs

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RoboStateProvider(private val sp: SharedPreferences) : AtomicStateProvider {

    override fun <T : Int?> int(id: String, default: T): ReadWriteProperty<Any?, T> {
        return GenericFsState(
            key = id,
            sp = sp,
            default = default,
            getter = { key, default -> getInt(key, default ?: -1) as T },
            setter = { key, value -> putInt(key, value ?: -1) }
        )
                as ReadWriteProperty<Any?, T>
    }

    override fun <T : String?> string(id: String, default: T): ReadWriteProperty<Any?, T> {
        return GenericFsState(
            key = id,
            sp = sp,
            default = default,
            getter = { key, default -> getString(key, default) as T },
            setter = { key, value -> putString(key, value) }
        )
    }
}

class GenericFsState<T>(
    private val key: String,
    private val sp: SharedPreferences,
    private val default: T,
    private val getter: SharedPreferences.(String, T) -> T,
    private val setter: SharedPreferences.Editor.(String, value: T) -> Unit,
) : ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!sp.contains(key)) {
            return default
        }

        return getter(sp, key, default)
    }

    @Suppress("UNCHECKED_CAST")
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (value != null) {
            val editor = sp.edit()

            setter(editor, key, value as T)

            editor.apply()
        } else {
            sp.edit()
                .remove(key)
                .apply()
        }
    }
}
