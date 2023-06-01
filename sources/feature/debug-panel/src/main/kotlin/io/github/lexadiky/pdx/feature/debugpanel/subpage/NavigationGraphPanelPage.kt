package io.github.lexadiky.pdx.feature.debugpanel.subpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.pdx.feature.debugpanel.DebugPanelPageModule

@Composable
internal fun NavigationGraphPanelPage() {
    DIFeature(DebugPanelPageModule) {
        NavigationGraphPanelPageImpl()
    }
}

@Composable
private fun NavigationGraphPanelPageImpl() {
    Text(text = "Not available with Nibbler")
}
