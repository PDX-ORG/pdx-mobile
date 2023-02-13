@file:OptIn(ExperimentalPagerApi::class)

package io.github.lexadiky.pdx.feature.spritegallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun SpriteGalleryPage(speciesId: String, varietyId: String) {
    DIFeature(SpriteGalleryPageModel) {
        SpriteGalleryPageImpl(
            di.viewModel(speciesId, varietyId)
        )
    }
}

@Composable
private fun SpriteGalleryPageImpl(viewModel: SpriteGalleryPageViewModel) {
    ErrorDialog(error = viewModel.state.error) {
        viewModel.hideError()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.grid.x2)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.extraLarge
            )
    ) {
        HorizontalPager(
            count = viewModel.state.items.size,
            userScrollEnabled = true
        ) { spriteIdx ->
            val (title, image) = viewModel.state.items[spriteIdx]

            Image(
                painter = image.render(
                    listOf(ImageTransformation.CropTransparent)
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.grid.x2)
            )
        }
    }
}
