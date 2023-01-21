package io.github.lexadiky.pdx.feature.whois.entity

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

data class WhoIsPokemonVariant(
    val image: ImageResource,
    val name: StringResource,
    val id: String
)