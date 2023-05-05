package io.github.lexadiky.pdx.feature.home

import androidx.compose.runtime.Composable
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.lib.arc.StaticSocket
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.lib.system.DefaultPreview
import io.github.lexadiky.pdx.lib.system.PagePreview

@[Composable DefaultPreview]
internal fun _HomePagePreview() = PagePreview {
    HomePageImpl(
        StaticSocket(
            state = HomePageState(
                featuredPokemon = generatePokemon(),
                latestViewedPokemon = generatePokemon()
            )
        )
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
