@file:OptIn(ExperimentalAnimationApi::class)

package io.github.lexadiky.pdx.feature.whois

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.whois.entity.WhoIsPokemonVariant
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.akore.lechuck.robo.decoration.Decoration
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.animation
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.ToolbarContent

private const val ANSWER_BUTTON_WIDTH_RATIO = 0.75f

@Composable
fun WhoIsPage() {
    DIFeature(WhoIsModule) {
        WhoIsPageImpl(viewModel = di.viewModel())
    }
}

@Composable
private fun WhoIsPageImpl(viewModel: WhoIsViewModel) {
    Decoration("pdx://toolbar/title") {
        ToolbarContent(
            start = {
                Text(text = stringResource(id = R.string.whois_title))
            },
            end = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(
                            id = R.string.whois_streak_label,
                            viewModel.state.streak
                        )
                    )
                }
            })
    }

    ErrorDialog(error = viewModel.state.error) {
        viewModel.hideError()
    }

    Column {
        AnimatedContent(
            label = "who-is-content",
            targetState = viewModel.state.whoisTarget,
            transitionSpec = {
                fadeIn() with fadeOut()
            },
            modifier = Modifier
                .aspectRatio(1f)
                .padding(horizontal = MaterialTheme.grid.x4)
                .fillMaxSize()
                .weight(1f)
        ) { state ->
            state?.let { target ->
                Image(
                    painter = target.image.render(
                        transformations = listOfNotNull(
                            ImageTransformation.CropTransparent,
                            ImageTransformation.Mask(color = Color.Gray)
                                .takeIf { viewModel.state.masked }
                        )
                    ),
                    contentDescription = null,
                )
            }
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(viewModel.state.currentVariants) { variant ->
                val buttonColor by buttonColorAsState(viewModel, variant)

                OutlinedButton(
                    onClick = { viewModel.onAnswer(variant) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.fillMaxWidth(ANSWER_BUTTON_WIDTH_RATIO)
                ) {
                    AnimatedContent(
                        targetState = variant.name.render(),
                        transitionSpec = {
                            fadeIn(MaterialTheme.animation.linearSlow()) with
                                    fadeOut(MaterialTheme.animation.linearSlow())
                        }
                    ) { name ->
                        Text(text = name)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
    }
}

@Composable
private fun buttonColorAsState(
    viewModel: WhoIsViewModel,
    variant: WhoIsPokemonVariant,
) = animateColorAsState(
    if (!viewModel.state.masked) {
        if (viewModel.state.whoisTarget == variant) {
            variant.color.render().copy(alpha = 0.2f)
        } else {
            MaterialTheme.colorScheme.errorContainer
        }
    } else {
        Color.White
    }
)
