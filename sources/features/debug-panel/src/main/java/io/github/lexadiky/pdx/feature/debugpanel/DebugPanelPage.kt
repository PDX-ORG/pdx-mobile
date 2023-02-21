@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.debugpanel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject

@Composable
internal fun DebugPanelPage() {
    DIFeature(DebugPanelPageModule) {
        DebugPanelPageImpl()
    }
}

@Composable
private fun DebugPanelPageImpl(viewModel: DebugPanelViewModel = di.inject()) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListItem(
                headlineText = { Text(text = "Give Debug Achievement") },
                trailingContent = { Icon(Icons.Default.Star, null) },
                modifier = Modifier.clickable { viewModel.giveAchievement() }
            )
        }
        item {
            ListItem(
                headlineText = { Text(text = "Navigation Schema") },
                trailingContent = { Icon(Icons.Default.ArrowForward, null) },
                modifier = Modifier.clickable { viewModel.openNavigationSchema() }
            )
        }
        item {
            ListItem(
                headlineText = { Text(text = "Force crash") },
                trailingContent = { Icon(Icons.Default.ExitToApp, null) },
                modifier = Modifier.clickable { viewModel.throwKashpirovskyException() }
            )
        }
    }
}
