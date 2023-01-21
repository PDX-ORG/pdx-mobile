@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.resources.renderNow
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
private fun AccountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialTheme.grid.x1)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(MaterialTheme.grid.x1)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
                    .size(MaterialTheme.grid.x4)
                    .clip(MaterialTheme.shapes.small)
            )
            Text(
                text = "User Name #232 a b c d e f g",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
            contentPadding = PaddingValues(horizontal = MaterialTheme.grid.x1),
            modifier = Modifier.padding(bottom = MaterialTheme.grid.x1)
        ) {
            repeat(3) {
                item {
                    AssistChip(
                        onClick = { /*TODO*/ },
                        label = { Text(text = "MyDex 23") }
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationItem(item: DrawerItem.Navigation, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(text = item.title.renderNow()) },
        icon = { Icon(item.icon.render(), null) },
        selected = item.selected,
        onClick = { onClick() },
    )
}