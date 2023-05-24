package io.github.lexadiky.pdx.library.dynbanner.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Banner(
    @SerialName("type")
    val type: BannerType,
    @SerialName("message")
    val message: String,
    @SerialName("icon")
    val icon: BannerIcon?,
    @SerialName("action")
    val action: BannerAction?
)
