package io.github.lexadiky.pdx.ui.uikit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class PdxGrid {

    val x05 = x(0.5f)
    val x1 = x(1f)
    val x1p5 = x(1.5f)
    val x2 = x(2f)
    val x4 = x(4f)
    val x8 = x(8f)

    fun x(multiplication: Float): Dp = 8.dp * multiplication

    companion object {

        internal val DEFAULT_INSTANCE = PdxGrid()
    }
}

val MaterialTheme.grid: PdxGrid get() = PdxGrid.DEFAULT_INSTANCE
