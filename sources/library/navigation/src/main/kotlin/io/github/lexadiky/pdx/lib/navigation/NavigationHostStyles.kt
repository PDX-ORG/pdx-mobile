package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.akore.lechuck.robo.style.BottomSheetStyle
import io.github.lexadiky.akore.lechuck.robo.style.NavigationHostStyle

object NavigationHostStyles {

    @Composable
    fun default(): NavigationHostStyle {
        val shape = MaterialTheme.shapes.extraLarge
        val background = MaterialTheme.colorScheme.surface

        return remember {
            NavigationHostStyle(
                bottomSheet = BottomSheetStyle(
                    shape = shape,
                    background = background
                )
            )
        }
    }
}
