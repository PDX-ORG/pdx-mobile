package io.github.lexadiky.pdx.feature.drawer.entity

import io.github.lexadiky.pdx.library.nibbler.Route
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

internal sealed interface DrawerItem {

    data class Navigation(
        val icon: ImageResource,
        val title: StringResource,
        val selected: Boolean,
        val route: Route
    ) : DrawerItem

    object Divider : DrawerItem
    object UserAccount : DrawerItem
}
