package io.github.lexadiky.pdx.lib.dynbanner.entity

import kotlinx.serialization.SerialName

data class BannerAction(
    @SerialName("type")
    val type: Type,
    @SerialName("route")
    val route: String
) {

    enum class Type {
        @SerialName("link_to")
        LinkTo
    }
}