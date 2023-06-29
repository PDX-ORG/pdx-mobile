package io.github.lexadiky.pdx.domain.account.entity

import java.util.UUID

data class UserAccount(
    val username: String,
    val trainerId: UUID
)