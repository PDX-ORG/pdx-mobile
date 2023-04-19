@file:OptIn(ExperimentalMaterialNavigationApi::class)
@file:Suppress("UnusedReceiverParameter")

package io.github.lexadiky.akore.lechuck.robo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.github.lexadiky.akore.lechuck.robo.fsdialog.FullScreenDialogLayout
import io.github.lexadiky.akore.lechuck.robo.fsdialog.rememberFullScreenDialogNavigator
import io.github.lexadiky.akore.lechuck.robo.style.NavigationHostStyle

@Composable
fun NavigationFeature(
    routing: NaviNavGraphBuilder.() -> Unit,
    startDestination: String,
    style: NavigationHostStyle,
    content: @Composable NavigationFeatureContext.() -> Unit,
) {
    val fullScreenDialogNavigator = rememberFullScreenDialogNavigator()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val controller = rememberNavController(bottomSheetNavigator, fullScreenDialogNavigator)

    val navGraph = remember(controller, routing) {
        val builder = NaviNavGraphBuilder(
            internal = NavGraphBuilder(
                provider = controller.navigatorProvider,
                startDestination = startDestination,
                route = null
            )
        )
        builder.apply(routing)
        controller.setViewModelStore(ViewModelStore())
        builder.build()
    }

    FullScreenDialogLayout(fullScreenDialogNavigator) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            sheetShape = style.bottomSheet.shape,
            sheetBackgroundColor = style.bottomSheet.background
        ) {
            WithLocalComposeNavigationContext(controller, navGraph) {
                NavigationFeatureContext.content()
            }
        }
    }
}

@Composable
fun NavigationFeatureContext.NavigationHost() {
    val context = LocalComposeNavigationContext.current
    NavHost(context.controller, context.navGraph)
}

/**
 * Marker class for narrowing the scope of navigation host installer functions
 *
 * @see NavigationHost
 */
object NavigationFeatureContext
