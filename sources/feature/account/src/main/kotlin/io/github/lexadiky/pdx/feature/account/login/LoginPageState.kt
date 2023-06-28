package io.github.lexadiky.pdx.feature.account.login

import java.util.UUID

data class LoginPageState(
    val username: String = ""
) {

    val trainerId: UUID = UUID.randomUUID()
    val localTrainerId: String = trainerId.toString().uppercase()
}