@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.lib.dynbanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
@Deprecated("requires rework")
fun DynamicBanner(
    id: String,
    modifier: Modifier = Modifier,
    isPlaceholderEnabled: Boolean = false,
) {
    DIFeature(DynamicBannerModule) {
        DynamicBannerImpl(viewModel = di.viewModel(id, isPlaceholderEnabled), modifier = modifier)
    }
}

@Composable
private fun DynamicBannerImpl(viewModel: DynamicBannerViewModel, modifier: Modifier) {
    viewModel.banner?.let { banner ->
        Card(
            onClick = { viewModel.onAction() },
            modifier = modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            )
                        )
                    )
                    .fillMaxWidth()
                    .padding(MaterialTheme.grid.x2)
            ) {
                Text(
                    text = banner.message,
                    modifier = Modifier.alignByBaseline()
                )
                banner.icon?.asImageResource()?.let { res ->
                    Image(
                        painter = res.render(),
                        contentDescription = null,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
        }
    }
}
