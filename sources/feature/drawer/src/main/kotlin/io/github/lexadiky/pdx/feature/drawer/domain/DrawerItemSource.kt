package io.github.lexadiky.pdx.feature.drawer.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.feature.drawer.entity.AuthInDrawerFeatureToggle
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.feature.drawer.entity.NewsInDrawerFeatureToggle
import io.github.lexadiky.pdx.library.featuretoggle.FeatureToggleManager
import io.github.lexadiky.pdx.library.nibbler.NavigateCommand
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.Route
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class DrawerItemSource(
    private val navigator: Navigator,
    private val toggleManager: FeatureToggleManager
) {

    suspend fun get(): Flow<List<DrawerItem>> {
        return navigator.navigateCommand.filterNotNull()
            .filterIsInstance<NavigateCommand.GoTo>()
            .map { currentRoute ->
                listOfNotNull(
                    DrawerItem.UserAccount
                        .takeIf { toggleManager.resolve(AuthInDrawerFeatureToggle) },
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Home),
                        title = StringResource.from(R.string.drawer_item_home_title),
                        route = "pdx://home",
                        currentRoute = currentRoute.uri
                    ),
                    DrawerItem.Divider,
                    createNavigationItem(
                        icon = ImageResource.from(UikitDrawable.uikit_ic_pokeball),
                        title = StringResource.from(R.string.drawer_item_pokemon_title),
                        route = "pdx://pokemon",
                        currentRoute = currentRoute.uri
                    ),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Star),
                        title = StringResource.from(R.string.drawer_item_type_chart_title),
                        route = "pdx://type",
                        currentRoute = currentRoute.uri
                    ),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Favorite),
                        title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_who_is),
                        route = "pdx://game/whois",
                        currentRoute = currentRoute.uri
                    ),
                    DrawerItem.Divider,
                    newsItem(currentRoute.route),
                    createNavigationItem(
                        icon = ImageResource.from(Icons.Default.Settings),
                        title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_settings_title),
                        route = "pdx://settings",
                        currentRoute = currentRoute.uri
                    )
                )
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
        route = Route.from(route)
    )

    private fun newsItem(currentRoute: Route?): DrawerItem? {
        return if (toggleManager.resolve(NewsInDrawerFeatureToggle)) {
            createNavigationItem(
                icon = ImageResource.from(Icons.Default.Notifications),
                title = StringResource.from(R.string.drawer_item_news_title),
                route = "pdx://news",
                currentRoute = currentRoute?.uri
            )
        } else {
            null
        }
    }
}
