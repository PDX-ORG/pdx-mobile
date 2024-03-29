package io.github.lexadiky.pdx.library.dynbanner.domain

import arrow.core.Either
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.dynbanner.entity.Banner
import io.github.lexadiky.pdx.library.fs.statist.StaticResourceProvider
import java.net.URI
import kotlin.reflect.typeOf

internal class DynamicBannerRepository(
    private val resourceProvider: StaticResourceProvider
) {
    suspend fun fetch(id: String): Either<GenericError, Banner> {
        val bannerDescriptor = resourceProvider.provide<Banner>(
            uri = URI.create("remote-config://firebase/dynamic_banner_$id"),
            ofType = typeOf<Banner>()
        )

        return bannerDescriptor.read()
            .mapLeft { error -> GenericError("can't fetch dynamic banner", error.reason) }
    }
}
