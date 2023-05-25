package io.github.lexadiky.pdx.library.microdata

interface MicrodataManager {

    fun acquire(owner: Any, database: String): MicrodataFactory
}
