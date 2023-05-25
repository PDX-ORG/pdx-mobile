package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListBannerDataSource
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListBanner
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericBannerItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem

class PokemonGenericBannerItemSource : GenericListBannerDataSource<PokemonGenericListItem> {

    override suspend fun load(): Either<GenericListBannerDataSource.Error, List<GenericListBanner<PokemonGenericListItem>>> {
        return Either.Right(listOf(
            PokemonGenericBannerItem(DONATE_AUTHOR_POSITION, "donate_author", "donate_author"),
            PokemonGenericBannerItem(WHO_IS_POSITION, "play_who_is", "play_who_is")
        ))
    }

    companion object {

        private const val DONATE_AUTHOR_POSITION = 10
        private const val WHO_IS_POSITION = 20
    }
}
