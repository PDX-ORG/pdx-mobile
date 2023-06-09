package io.github.lexadiky.pdx.library.core.fts

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

        fun build(source: Collection<String>): FtsIndex =
            MatrixFtsIndex(source.size)
                .apply { source.forEach(::addClause) }
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
    fun addClause(closure: String)
}

/**
 * Implementation of [MutableFtsIndex] linearly searching for exact sequence partial match.
 */
internal class MatrixFtsIndex(baseSize: Int = EXPECTED_INDEX_SIZE) : MutableFtsIndex {

    private val matrix: MutableList<String> = ArrayList(baseSize)

    override fun addClause(closure: String) {
        matrix += closure.lowercase()
    }

    override fun matches(query: String): Boolean {
        if (query.isBlank()) {
            return false
        }

        val sanitizedQuery = query.lowercase()
            .trim()

        return matrix.any { closure -> sanitizedQuery in closure }
    }

    companion object {

        private const val EXPECTED_INDEX_SIZE = 16
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
