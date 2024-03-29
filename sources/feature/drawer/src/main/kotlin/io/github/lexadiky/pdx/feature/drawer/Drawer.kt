package io.github.lexadiky.pdx.feature.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.drawer.accountcard.AccountCard
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
fun Drawer() {
    DIFeature(DrawerModule) {
        DrawerImpl(di.viewModel())
    }
}

@Composable
internal fun DrawerImpl(viewModel: DrawerViewModel = di.viewModel("default")) {
    for (item in viewModel.state.items) {
        when (item) {
            is DrawerItem.Navigation -> {
                NavigationItem(item) {
                    viewModel.onItemClicked(item)
                    
                }
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
