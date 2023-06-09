package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.IsPokemonFavoriteUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetAllPokemonPreviewsUseCase
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.library.core.fts.FtsIndex
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.util.UikitStringFormatter

internal class PokemonGenericListItemSource(
    private val getPokemon: GetAllPokemonPreviewsUseCase,
    private val isPokemonFavorite: IsPokemonFavoriteUseCase
) : GenericListItemDataSource<PokemonGenericListItem> {

    override suspend fun load(): Either<GenericListItemDataSource.Error, List<PokemonGenericListItem>> {
        return getPokemon()
            .map { pokemons -> pokemons.map { pokemon -> transformToGenericListItem(pokemon) } }
            .mapLeft { GenericListItemDataSource.Error.Generic }
    }

    private suspend fun transformToGenericListItem(pokemon: PokemonPreview) =
        PokemonGenericListItem(
            id = pokemon.name,
            note = UikitStringFormatter.nationalId(pokemon.nationalDexNumber),
            title = StringResource.from(pokemon.localeName),
            primaryImage = pokemon.normalSprite?.let { ImageResource.from(it) }
                ?: ImageResource.from(UikitDrawable.uikit_ic_pokeball),
            secondaryImage = pokemon.shinySprite?.let { ImageResource.from(it) }
                ?: ImageResource.from(UikitDrawable.uikit_ic_pokeball),
            tags = pokemon.types.map { type ->
                GenericListItem.Tag(type.assets.title, type.assets.color, type.id)
            },
            types = pokemon.types,
            isFavorite = isPokemonFavorite.invoke(pokemon),
            searchIndex = pokemon.searchIndex
        )
}
