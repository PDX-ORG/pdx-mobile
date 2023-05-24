package io.github.lexadiky.pdx.ui.uikit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("MagicNumber")
class PdxGrid {

    val x05 = x(0.5f)
    val x1 = x(1f)
    val x1p5 = x(1.5f)
    val x2 = x(2f)
    val x3 = x(3f)
    val x4 = x(4f)
    val x5 = x(5f)
    val x8 = x(8f)
    val x10 = x(10f)
    val x12 = x(12f)
    val x20 = x(20f)

    val s1 = 1.dp

    private fun x(multiplication: Float): Dp = 8.dp * multiplication

    companion object {

        internal val DEFAULT_INSTANCE = PdxGrid()
    }
}

@Suppress("UnusedReceiverParameter")
val MaterialTheme.grid: PdxGrid get() = PdxGrid.DEFAULT_INSTANCE
