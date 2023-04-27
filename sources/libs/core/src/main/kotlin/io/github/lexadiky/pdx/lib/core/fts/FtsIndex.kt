package io.github.lexadiky.pdx.lib.core.fts

/**
 * Abstraction over full text search algorithm.
 */
interface FtsIndex {

    /**
     * Checks if [query] is matching against this index.
     * If query is empty it will not match against anything.
     *
     * Should be thread safe.
     */
    fun matches(query: String): Boolean

    companion object {

        /**
         * Returns mutable variation of index.
         * Useful for cleaning new indexes
         */
        fun buildable(): MutableFtsIndex = MatrixFtsIndex()
    }
}

/**
 * Mutable variation of [FtsIndex]
 */
interface MutableFtsIndex : FtsIndex {

    /**
     * Adds new simple closure into index.
     *
     * Not thread safe.
     */
    fun addClosure(closure: String)
}

/**
 * Implementation of [MutableFtsIndex] linearly searching for exact sequence partial match.
 */
private class MatrixFtsIndex : MutableFtsIndex {

    private val matrix: MutableList<String> = ArrayList(16)

    override fun addClosure(closure: String) {
        matrix += closure.lowercase()
    }

    override fun matches(query: String): Boolean {
        if (query.isEmpty()) {
            return false
        }

        val sanitizedQuery = query.lowercase()

        return matrix.any { closure -> sanitizedQuery in closure }
    }
}

/**
 * Analogue to [FtsIndex.matches] but will return true for empty query
 */
fun FtsIndex.matchesOrEmpty(query: String): Boolean {
    if (query.isEmpty()) {
        return true
    } else {
        return matches(query)
    }
}
