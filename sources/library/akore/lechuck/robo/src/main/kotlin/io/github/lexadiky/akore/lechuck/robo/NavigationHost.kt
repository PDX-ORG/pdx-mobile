@file:OptIn(ExperimentalMaterialNavigationApi::class)
@file:Suppress("UnusedReceiverParameter")

package io.github.lexadiky.akore.lechuck.robo

import androidx.compose.runtime.Composable
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
    startDestination: String,
    style: NavigationHostStyle,
    content: @Composable NavigationFeatureContext.() -> Unit,
) {
    val fullScreenDialogNavigator = rememberFullScreenDialogNavigator()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val controller = rememberNavController(bottomSheetNavigator, fullScreenDialogNavigator)

    FullScreenDialogLayout(fullScreenDialogNavigator) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            sheetShape = style.bottomSheet.shape,
            sheetBackgroundColor = style.bottomSheet.background
        ) {
            WithLocalComposeNavigationContext(controller, startDestination) {
                NavigationFeatureContext.content()
            }
        }
    }
}

@Composable
fun NavigationFeatureContext.NavigationHost(routing: NaviNavGraphBuilder.() -> Unit) {
    val context = LocalComposeNavigationContext.current
    NavHost(context.controller, context.startDestination) {
        NaviNavGraphBuilder(this).apply(routing)
    }
}

/**
 * Marker class for narrowing the scope of navigation host installer functions
 *
 * @see NavigationHost
 */
object NavigationFeatureContext
