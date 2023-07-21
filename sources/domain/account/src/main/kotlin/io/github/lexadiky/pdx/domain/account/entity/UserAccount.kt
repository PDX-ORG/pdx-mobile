package io.github.lexadiky.pdx.domain.account.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    @SerialName("username")
    val username: String,
    @SerialName("trainer_id")
    val trainerId: String,
    @SerialName("avatar_uri")
    val avatarUrl: String?
) {

    val localTrainerId get() = trainerId.uppercase()
}