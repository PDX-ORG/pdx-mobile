package io.github.lexadiky.akore.lechuck.robo.fsdialog

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import io.github.lexadiky.akore.lechuck.robo.LocalComposeNavigationContext

data class FullScreenDialogAnchor(
    val tag: String,
    val coordinates: LayoutCoordinates,
    val style: FullScreenDialogStyle
)

@Composable
fun FullScreenDialogAnchor(
    tag: String = FullScreenDialogNavigator.DEFAULT_FS_DIALOG_TAG,
    style: FullScreenDialogStyle,
) {
    val akoreNavigator = LocalComposeNavigationContext.current.roboNavigator
    val jpnNavigator = remember(akoreNavigator) {
        akoreNavigator.controller.navigatorProvider
            .getNavigator(FullScreenDialogNavigator::class.java)
    }
    Box(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            jpnNavigator.saveAnchor(tag, FullScreenDialogAnchor(tag, coordinates, style))
        }
    )
}
