package io.github.lexadiky.pdx.library.uikit.image

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import io.github.lexadiky.pdx.library.uikit.UikitDrawable

object CustomIcons {

    val Reddit: Painter @Composable get() = painterResource(id = UikitDrawable.uikit_ic_reddit)
}

val Icons.Filled.Custom: CustomIcons get() = CustomIcons
