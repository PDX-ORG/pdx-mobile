package io.github.lexadiky.pdx.library.uikit.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.verbose

@Composable
internal fun rememberAsyncImagePainter(url: String, builder: ImageRequest.Builder.() -> Unit = {}): Painter {
    if (url.isBlank()) {
        BLogger.tag("rememberAsyncImagePainter")
            .error("can't load image from empty url")
    }

    val context = LocalContext.current

    val request = remember(url, builder) {
        ImageRequest.Builder(context)
            .data(url)
            .apply(builder)
            .build()
    }

    return coil.compose.rememberAsyncImagePainter(
        model = request,
        imageLoader = LocalImageLoader.current,
        filterQuality = FilterQuality.None,
        onState = { state ->
            when (state) {
                is AsyncImagePainter.State.Error ->
                    BLogger.tag("rememberAsyncImagePainter")
                        .error("can't load image: $url", state.result.throwable)
                else ->
                    BLogger.tag("rememberAsyncImagePainter")
                        .verbose("image loading status ${state::class}")
            }
        }
    )
}
