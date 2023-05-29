package io.github.lexadiky.pdx.feature.drawer.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.akore.lechuck.NavigationRoute
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.feature.drawer.entity.AuthInDrawerFeatureToggle
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.library.featuretoggle.FeatureToggleManager
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class DrawerItemSource(
    private val navigator: Navigator,
    private val toggleManager: FeatureToggleManager
) {

    suspend fun get(): Flow<List<DrawerItem>> {
        return navigator.currentRoute.filterNotNull()
            .map { currentRoute ->
                listOfNotNull(
                    DrawerItem.UserAccount
                        .takeIf { toggleManager.resolve(AuthInDrawerFeatureToggle) },
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Home),
                        title = StringResource.from(R.string.drawer_item_home_title),
                        route = "pdx://home",
                        currentRoute = currentRoute.asString()
                    ),
                    DrawerItem.Divider,
                    createNavigationItem(
                        icon = ImageResource.from(UikitDrawable.uikit_ic_pokeball),
                        title = StringResource.from(R.string.drawer_item_pokemon_title),
                        route = "pdx://pokemon",
                        currentRoute = currentRoute.asString()
                    ),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Star),
                        title = StringResource.from(R.string.drawer_item_type_chart_title),
                        route = "pdx://type",
                        currentRoute = currentRoute.asString()
                    ),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Favorite),
                        title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_who_is),
                        route = "pdx://game/whois",
                        currentRoute = currentRoute.asString()
                    ),
                    DrawerItem.Divider,
                    newsItem(currentRoute),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Settings),
                        title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_settings_title),
                        route = "pdx://settings",
                        currentRoute = currentRoute.asString()
                    ),
                    DrawerItem.Divider
                        .takeIf { toggleManager.resolve(AuthInDrawerFeatureToggle) },
                    DrawerItem.Login
                        .takeIf { toggleManager.resolve(AuthInDrawerFeatureToggle) }
                ) + debugPanelItem(currentRoute)
            }
    }

    private fun createNavigationItem(
        icon: ImageResource,
        title: StringResource,
        route: String,
        currentRoute: String?,
    ) = DrawerItem.Navigation(
        icon = icon,
        title = title,
        selected = route == currentRoute,
        route = NavigationRoute.from(route)
    )

    private suspend fun newsItem(currentRoute: NavigationRoute?): DrawerItem? {
        return if (navigator.hasRoute(NavigationRoute.from("pdx://news"))) {
            createNavigationItem(
                icon = ImageResource.from(Icons.Default.Notifications),
                title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_news_title),
                route = "pdx://news",
                currentRoute = currentRoute?.asString()
            )
        } else {
            null
        }
    }

    private suspend fun debugPanelItem(currentRoute: NavigationRoute?): Array<DrawerItem> {
        return if (navigator.hasRoute(NavigationRoute.from("pdx://debug/panel"))) {
            arrayOf(
                DrawerItem.Divider,
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_debug_panel_title),
                    route = "pdx://debug/panel",
                    currentRoute = currentRoute?.asString()
                )
            )
        } else {
            emptyArray()
        }
    }
}