package io.github.lexadiky.pdx.feature.whois.entity

import io.github.lexadiky.pdx.library.resources.color.ColorResource
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

data class WhoIsPokemonVariant(
    val image: ImageResource,
    val color: ColorResource,
    val name: StringResource,
    val id: String
)
