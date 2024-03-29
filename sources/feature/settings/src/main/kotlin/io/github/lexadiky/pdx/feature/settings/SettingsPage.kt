package io.github.lexadiky.pdx.feature.settings

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.library.nibbler.decoration.Decoration
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
fun SettingsPage() {
    DIFeature(SettingsPageModule) {
        SettingsPageImpl(di.viewModel())
    }
}

@Composable
private fun SettingsPageImpl(viewModel: SettingsPageViewModel) {
    Decoration("pdx://toolbar/title") {
        Text(stringResource(id = R.string.settings_toolbar_title))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(
                    id = R.string.settings_themes_header,
                    viewModel.state.currentTheme?.id.orEmpty()
                ),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(MaterialTheme.grid.x2)
            )
            ThemeSelection(viewModel.state) {
                viewModel.onThemeSelected(it)
            }
        }
        item {
            Divider()
        }
        item {
            ListItem(
                headlineContent = { Text(text = stringResource(id = R.string.settings_language_use_romaji_header)) },
                supportingContent = { Text(text = stringResource(id = R.string.settings_language_use_romaji_description)) },
                trailingContent = {
                    Switch(
                        checked = viewModel.state.romajiEnabled,
                        onCheckedChange = { viewModel.switchRomaji(it) }
                    )
                },
                modifier = Modifier.clickable { viewModel.switchRomaji(!viewModel.state.romajiEnabled) }
            )
        }
        item {
            Divider()
        }
        item {
            ListItem(
                headlineContent = { Text(text = stringResource(id = R.string.settings_achievements_header)) },
                supportingContent = { Text(text = stringResource(id = R.string.settings_achievements_description)) },
                trailingContent = {
                    Icon(Icons.Default.Star, null)
                },
                modifier = Modifier.clickable { viewModel.openAchievements() }
            )
        }
        item {
            ListItem(
                headlineContent = { Text(text = stringResource(id = R.string.settings_github_header)) },
                supportingContent = { Text(text = stringResource(id = R.string.settings_github_description)) },
                trailingContent = {
                    Icon(painterResource(UikitDrawable.uikit_ic_github), null)
                },
                modifier = Modifier.clickable { viewModel.openGithub() }
            )
        }
        item {
            ListItem(
                headlineContent = { Text(text = stringResource(id = R.string.settings_cache_header)) },
                supportingContent = { Text(text = stringResource(id = R.string.settings_cache_description)) },
                trailingContent = {
                    Icon(Icons.Default.Delete, null)
                },
                modifier = Modifier.clickable { viewModel.dropCaches() }
            )
        }
    }
}

@Composable
private fun ThemeSelection(state: SettingsPageState, onThemeSelected: (CustomTheme) -> Unit) {
    val sizeSmall = MaterialTheme.grid.x10
    val sizeBig = MaterialTheme.grid.x12

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = MaterialTheme.grid.x2),
        modifier = Modifier
            .height(sizeBig + MaterialTheme.grid.x2 * 2)
    ) {
        items(state.availableThemes) { theme ->
            ThemeBox(
                sizeBig = sizeBig,
                sizeSmall = sizeSmall,
                selectedTheme = state.currentTheme,
                theme = theme,
                onClick = { onThemeSelected(theme) }
            )
        }
    }
}

@Composable
private fun ThemeBox(
    sizeBig: Dp,
    sizeSmall: Dp,
    selectedTheme: CustomTheme?,
    theme: CustomTheme,
    onClick: () -> Unit
) {
    val size by animateDpAsState(if (selectedTheme?.id == theme.id) sizeBig else sizeSmall)
    val cornerRadius by animateDpAsState(if (selectedTheme?.id == theme.id) sizeSmall else 32.dp)

    Box(
        Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .clickable { onClick() }
            .background(theme.colorScheme.primary)
            .size(size)
    )
}
