package io.github.lexadiky.pdx.feature.move.details.entity.attribute

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

sealed interface MoveAttribute {

    class Text(
        val title: StringResource,
        val icon: ImageResource,
        val value: StringResource
    ): MoveAttribute
}