package io.github.lexadiky.pdx.feature.account.details.entity

import io.github.lexadiky.pdx.library.resources.color.ColorResource
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

data class CandyBoxState(
    val color: ColorResource,
    val title: StringResource,
    val badges: List<ImageResource>
)
