package io.github.lexadiky.pdx.feature.drawer.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.lib.ifEnabled
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.resources.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class DrawerItemSource(private val navigator: Navigator) {

    suspend fun get(): Flow<List<DrawerItem>> {
        return navigator.currentAbsoluteRouteFlow.map { currentRoute ->
            listOfNotNull(
                DrawerItem.UserAccount,
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Home),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_home_title),
                    route = "pdx://home",
                    currentRoute = currentRoute
                ),
                DrawerItem.Divider,
                createNavigationItem(
                    icon = ImageResource.from(R.drawable.uikit_ic_pokeball),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_pokemon_title),
                    route = "pdx://pokemon",
                    currentRoute = currentRoute
                ),
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Star),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_type_chart_title),
                    route = "pdx://type",
                    currentRoute = currentRoute
                ),
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Favorite),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_who_is),
                    route = "pdx://game/whois",
                    currentRoute = currentRoute
                ),
                DrawerItem.Divider,
                newsItem(currentRoute),
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Settings),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_settings_title),
                    route = "pdx://settings",
                    currentRoute = currentRoute
                ),
                DrawerItem.Divider,
                DrawerItem.Login,
                *debugPanelItem(currentRoute)
            )
        }
    }

    private fun createNavigationItem(
        icon: ImageResource,
        title: StringResource,
        route: String,
        currentRoute: String?
    ) = DrawerItem.Navigation(
        icon = icon,
        title = title,
        selected = route == currentRoute,
        route = route
    )

    private suspend fun newsItem(currentRoute: String?): DrawerItem? {
        return if (navigator.hasRoute("pdx://news")) {
            createNavigationItem(
                icon = ImageResource.from(Icons.Default.Notifications),
                title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_news_title),
                route = "pdx://news",
                currentRoute = currentRoute
            )
        } else {
            null
        }
    }

    private suspend fun debugPanelItem(currentRoute: String?): Array<DrawerItem> {
        return if (navigator.hasRoute("pdx://debug/panel")) {
            arrayOf(
                DrawerItem.Divider,
                createNavigationItem(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_debug_panel_title),
                    route = "pdx://debug/panel",
                    currentRoute = currentRoute
                )
            )
        } else {
            emptyArray()
        }
    }
}