package io.github.lexadiky.pdx.library.dynbanner.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.ui.uikit.resources.from
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerIcon(
    val type: Type,
    val resource: String,
) {

    enum class Type {
        @SerialName("local")
        Local
    }

    fun asImageResource(): ImageResource = when (type) {
        Type.Local -> asLocalImageResource()
    }

    private fun asLocalImageResource(): ImageResource = when (resource) {
        "ic.share" -> ImageResource.from(Icons.Default.Share)
        else -> ImageResource.from(Icons.Default.Star)
    }
}
