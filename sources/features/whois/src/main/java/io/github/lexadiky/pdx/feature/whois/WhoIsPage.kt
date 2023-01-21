@file:OptIn(ExperimentalAnimationApi::class)

package io.github.lexadiky.pdx.feature.whois

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.resources.renderNow
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun WhoIsPage() {
    DIFeature(WhoIsModule) {
        WhoIsPageImpl(viewModel = di.inject())
    }
}

@Composable
private fun WhoIsPageImpl(viewModel: WhoIsViewModel) {
    ErrorDialog(error = viewModel.state.error) {
        viewModel.hideError()
    }

    Column {
        AnimatedContent(
            targetState = viewModel.state.whoisTarget,
            transitionSpec = {
                expandIn(expandFrom = Alignment.Center) with
                        shrinkOut(shrinkTowards = Alignment.Center)
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


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.state.currentVariants.forEach { variant ->
                Button(
                    onClick = { viewModel.onAnswer(variant) },
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Text(text = variant.name.renderNow())
                }
            }
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
        }
    }
}