@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.typechart

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartPage
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchPage
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.ToggleableFab

@Composable
fun TypePage() {
    DIFeature(TypeModule) {
        TypePageImpl()
    }
}

@Composable
private fun TypePageImpl() {
    var isSearchEnabled by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(
            label = "type_page_corssfade",
            targetState = isSearchEnabled
        ) { isSearchEnabledState ->
            if (isSearchEnabledState) {
                TypeSearchPage()
            } else {
                TypeChartPage()
            }
        }

        ToggleableFab(
            isToggled = isSearchEnabled,
            toggled = { Icon(Icons.Default.Close, null) },
            untoggled = { Icon(Icons.Default.Search, null) },
            onToggle = { isSearchEnabled = !isSearchEnabled },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.grid.x2)
        )
    }
}
