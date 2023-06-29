package io.github.lexadiky.pdx.feature.account.login

import java.util.UUID

data class LoginPageState(
    val username: String = "",
    val inputEnabled: Boolean = true,
    val usernameIsInvalid: Boolean = false
) {

    val trainerId: UUID = UUID.randomUUID()
    val localTrainerId: String = trainerId.toString().uppercase()
}