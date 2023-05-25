package io.github.lexadiky.pdx.feature.generic.list.entity

import io.github.lexadiky.pdx.library.resources.color.ColorResource
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

interface GenericListItem {

    val id: String
    val title: StringResource
    val note: StringResource
    val primaryImage: ImageResource
    val secondaryImage: ImageResource?
    val tags: List<Tag>
    val isFavorite: Boolean

    data class Tag(val text: StringResource, val color: ColorResource, val id: String)
}
