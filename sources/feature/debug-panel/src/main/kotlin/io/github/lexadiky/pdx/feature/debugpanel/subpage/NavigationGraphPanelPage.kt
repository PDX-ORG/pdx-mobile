package io.github.lexadiky.pdx.feature.debugpanel.subpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.pdx.feature.debugpanel.DebugPanelPageModule
import kotlinx.coroutines.runBlocking

@Composable
internal fun NavigationGraphPanelPage() {
    DIFeature(DebugPanelPageModule) {
        NavigationGraphPanelPageImpl()
    }
}

@Composable
private fun NavigationGraphPanelPageImpl() {
    val navigator = di.inject<Navigator>()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        // TODO remove run blocking
        items(runBlocking { navigator.routes() }) { route ->
            ListItem(
                headlineContent = { Text(route.asString()) }
            )
        }
    }
}
