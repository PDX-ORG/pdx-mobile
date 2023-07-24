package io.github.lexadiky.pdx.feature.account.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.feature.account.R
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.util.saturation
import io.github.lexadiky.pdx.library.uikit.widget.BottomSheetBasement
import io.github.lexadiky.pdx.library.uikit.widget.scaffold.BottomSheetHeaderScaffold

@Composable
fun AccountDetailsPage() {
    DIFeature(AccountDetailsModule) {
        AccountDetailsPageImpl()
    }
}

@Composable
private fun AccountDetailsPageImpl(vm: AccountDetailsSocket = di.viewModel()) = Page(vm) { state, act ->
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
    ) {
        item {
            BottomSheetHeaderScaffold(
                icon = {
                    Image(
                        painter = state.avatar.render(
                            transformations = listOf(ImageTransformation.CropTransparent)
                        ),
                        contentDescription = stringResource(id = R.string.account_details_avatar_placeholder),
                        modifier = Modifier.size(MaterialTheme.grid.x4)
                    )
                },
                title = {
                    Text(
                        text = state.username
                            ?: stringResource(id = R.string.account_details_default_username)
                    )
                },
                modifier = Modifier.padding(MaterialTheme.grid.x2)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = MaterialTheme.grid.x2),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
            ) {
                items(30) {
                    CandyBox(
                        color = PokemonTypeAssets.values().random().color.render()
                    )
                }
            }
        }
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { act(AccountDetailsAction.OnLogoutClicked) }) {
                    Text(text = stringResource(id = R.string.account_details_logout_button))
                }
            }
        }
        item {
            BottomSheetBasement()
        }
    }
}

private const val CANDY_BOX_SATURATION_TOP = 1.2f
private const val CANDY_BOX_SATURATION_BOTTOM = 0.5f

@Composable
fun CandyBox(
    color: Color
) {
    Box(
        modifier = Modifier
            .size(MaterialTheme.grid.x20)
            .shadow(
                elevation = MaterialTheme.grid.x1,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .clickable {

            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        color.saturation(CANDY_BOX_SATURATION_TOP),
                        color.saturation(CANDY_BOX_SATURATION_BOTTOM)
                    )
                )
            )
            .clip(shape = MaterialTheme.shapes.extraLarge)
    )
}
