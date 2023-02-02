package io.github.lexadiky.pdx.lib.fs

import kotlin.properties.ReadWriteProperty

interface AtomicStateProvider {

    fun <T : Int?> int(id: String, default: T): ReadWriteProperty<Any?, T>

    fun <T : String?> string(id: String, default: T): ReadWriteProperty<Any?, T>
}
