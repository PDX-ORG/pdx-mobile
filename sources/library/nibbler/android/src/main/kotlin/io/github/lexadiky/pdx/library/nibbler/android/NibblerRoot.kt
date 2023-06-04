@file:OptIn(ExperimentalMaterialNavigationApi::class)

package io.github.lexadiky.pdx.library.nibbler.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.library.nibbler.NavigateCommand
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.NibblerModule
import io.github.lexadiky.pdx.library.nibbler.Route
import io.github.lexadiky.pdx.library.nibbler.android.graph.RoutingBuilder
import io.github.lexadiky.pdx.library.nibbler.android.graph.RoutingBuilderContext

private typealias NibblerRootContent = @Composable () -> Unit

@Composable
fun NibblerRoot(builder: RoutingBuilder, installer: @Composable (NibblerRootContent) -> Unit) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val composeNavigator = rememberNavController(
        bottomSheetNavigator
    )

    DIFeature(NibblerModule(composeNavigator)) {
        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            installer {
                // nibbler
                val nibblerNavigator = di.inject<Navigator>()
                val navigateCommand by nibblerNavigator.navigateCommand.collectAsState()

                // jetpack


                NavHost(composeNavigator, Route.INDEX.uri) {
                    RoutingBuilderContext(this)
                        .apply(builder)
                }

                LaunchedEffect(navigateCommand) {
                    when (navigateCommand) {
                        is NavigateCommand.GoTo -> composeNavigator.navigate(
                            (navigateCommand as NavigateCommand.GoTo).uri
                        )
                    }
                }
            }
        }
    }
}

