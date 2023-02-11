@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.feature.drawer.accountcard.AccountCard
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun Drawer() {
    DIFeature(DrawerModule) {
        DrawerImpl(di.inject())
    }
}

@Composable
internal fun DrawerImpl(viewModel: DrawerViewModel) {
    for (item in viewModel.state.items) {
        when (item) {
            is DrawerItem.Navigation -> {
                NavigationItem(item) { viewModel.onItemClicked(item) }
            }
            is DrawerItem.Divider -> {
                Divider(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.grid.x1)
                )
            }
            is DrawerItem.UserAccount -> {
                AccountCard()
            }
            is DrawerItem.Login -> {
                NavigationDrawerItem(
                    label = { Text("Login / Register") },
                    icon = { Icon(Icons.Default.Lock, null) },
                    onClick = { viewModel.onItemClicked(item) },
                    selected = false
                )
            }
        }
    }
}

@Composable
private fun NavigationItem(item: DrawerItem.Navigation, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(text = item.title.render()) },
        icon = { Icon(item.icon.render(), null) },
        selected = item.selected,
        onClick = { onClick() },
    )
}
