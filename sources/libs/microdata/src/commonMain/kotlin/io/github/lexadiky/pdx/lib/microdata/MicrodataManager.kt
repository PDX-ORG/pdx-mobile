package io.github.lexadiky.pdx.lib.microdata

interface MicrodataManager {

    fun acquire(owner: Any, database: String): Microdata
}
