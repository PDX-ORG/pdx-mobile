@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.settings.achievement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.settings.R
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.render

@Composable
fun AchievementSettingsPage() {
    DIFeature(AchievementSettingsModule) {
        AchievementSettingsPageImpl()
    }
}

@Composable
private fun AchievementSettingsPageImpl(viewModel: AchievementSettingsViewModel = di.viewModel()) {
    LazyColumn {
        items(viewModel.state.availableAchievements) { achievement ->
            ListItem(
                headlineContent = { Text(achievement.name.render()) },
                supportingContent = { Text(achievement.description.render()) },
                trailingContent = {
                    Icon(
                        painter = achievement.icon?.render()
                            ?: painterResource(id = UikitDrawable.uikit_ic_pokeball),
                        contentDescription = null
                    )
                }
            )
        }
        if (viewModel.state.availableAchievements.isNotEmpty()) {
            item { Divider() }
            item {
                ListItem(
                    headlineContent = { Text(stringResource(id = R.string.settings_achievements_option_forget_title)) },
                    supportingContent = { Text(stringResource(id = R.string.settings_achievements_option_forget_subtitle)) },
                    trailingContent = { Icon(Icons.Default.Delete, null) },
                    modifier = Modifier.clickable { viewModel.dropAllAchievementData() }
                )
            }
        } else {
            item {
                ListItem(
                    headlineContent = { Text(stringResource(id = R.string.settings_achievements_option_onboarding_title)) },
                    supportingContent = { Text(
                        stringResource(id = R.string.settings_achievements_option_onboarding_subtitle)
                    ) },
                    trailingContent = { Icon(Icons.Default.Star, null) },
                )
            }
        }
    }
}
