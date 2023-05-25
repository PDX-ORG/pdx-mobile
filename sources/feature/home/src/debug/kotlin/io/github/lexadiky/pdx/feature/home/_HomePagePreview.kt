package io.github.lexadiky.pdx.feature.home

import androidx.compose.runtime.Composable
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.system.DefaultPreview
import io.github.lexadiky.pdx.library.system.PagePreview

@[Composable DefaultPreview]
internal fun _HomePagePreview() = PagePreview {
    HomePageImpl(
        object : HomePageSocket(
            HomePageState(
                featuredPokemon = generatePokemon(),
                latestViewedPokemon = generatePokemon()
            )
        ) {
            override suspend fun onAction(action: HomePageAction) = Unit
        }
    )
}

private const val RAW_LENGTH = 3
private fun generatePokemon(): List<SuggestedPokemonItem> = List(RAW_LENGTH) { idx ->
    SuggestedPokemonItem(
        name = StringResource.from("Ditto"),
        image = ImageResource.from("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png"),
        nationalDexId = StringResource.from("#21"),
        id = "sample-$idx"
    )
}
