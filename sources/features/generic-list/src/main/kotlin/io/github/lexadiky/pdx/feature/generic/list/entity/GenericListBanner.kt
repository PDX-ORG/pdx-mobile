package io.github.lexadiky.pdx.feature.generic.list.entity

interface GenericListBanner<T: GenericListItem> {
    val id: String
    val bannerId: String

    fun shouldPlace(position: Int, item: T): Boolean
}
