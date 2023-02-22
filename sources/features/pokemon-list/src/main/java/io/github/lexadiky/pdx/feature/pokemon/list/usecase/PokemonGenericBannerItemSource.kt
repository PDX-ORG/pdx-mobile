package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListBannerDataSource
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListBanner
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericBannerItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem

class PokemonGenericBannerItemSource : GenericListBannerDataSource<PokemonGenericListItem> {

    override suspend fun load(): Either<GenericListBannerDataSource.Error, List<GenericListBanner<PokemonGenericListItem>>> {
        return Either.Right(listOf(
            PokemonGenericBannerItem(10, "donate_author", "donate_author"),
            PokemonGenericBannerItem(20, "play_who_is", "play_who_is")
        ))
    }
}
