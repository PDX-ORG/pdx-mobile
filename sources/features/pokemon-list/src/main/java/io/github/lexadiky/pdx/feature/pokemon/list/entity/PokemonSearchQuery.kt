package io.github.lexadiky.pdx.feature.pokemon.list.entity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.navigation.page.PageContext

data class PokemonSearchQuery(
    val text: String = "",
    val selectedTypes: List<PokemonType> = emptyList()
) : SearchQuery<PokemonGenericListItem> {

    override val isEmpty: Boolean = text.isBlank() && selectedTypes.isEmpty()

    override fun apply(items: List<PokemonGenericListItem>): List<PokemonGenericListItem> {
        return items
            .filter { item -> item.textSearchIndex.contains(text.lowercase()) }
            .filter { item -> item.types.containsAll(selectedTypes) }
    }

    companion object
}

@Composable
fun PokemonSearchQuery.Companion.parse(context: PageContext): PokemonSearchQuery {
    val types = context.argument("types") { "" }
    return remember(types) {
        val requestedTypes = types.split(",")

        PokemonSearchQuery(
            selectedTypes = PokemonType.values()
                .filter { it.id in requestedTypes }
        )
    }
}
