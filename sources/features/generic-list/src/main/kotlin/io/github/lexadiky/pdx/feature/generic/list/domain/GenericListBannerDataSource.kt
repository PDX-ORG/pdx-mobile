package io.github.lexadiky.pdx.feature.generic.list.domain

import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListBanner
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem

interface GenericListBannerDataSource<T: GenericListItem> {

    suspend fun load(): Either<Error, List<GenericListBanner<T>>>

    object Error
}
