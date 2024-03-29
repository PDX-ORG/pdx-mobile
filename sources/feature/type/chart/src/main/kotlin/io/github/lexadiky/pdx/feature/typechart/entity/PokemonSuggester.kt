package io.github.lexadiky.pdx.feature.typechart.entity

internal class PokemonSuggester {

    fun suggest(all: List<PokemonTypeSearchItem>, query: String): Suggestion {
        val preparedQuery = query.lowercase()
        val hints = all.filter { item -> item.searchQueryIndex.matches(preparedQuery) }
            .take(SUGGESTION_SIZE)
        val mostLikely = hints.firstOrNull()

        return Suggestion(
            hints = hints,
            mostLikely = mostLikely
        )
    }

    data class Suggestion(
        val hints: List<PokemonTypeSearchItem>,
        val mostLikely: PokemonTypeSearchItem?
    )

    companion object {

        private const val SUGGESTION_SIZE = 4
    }
}
