package io.github.lexadiky.pdx.domain.achievement

import io.github.lexadiky.akore.alice.module

val AchievementModule by module("achievement") {
    single { AchievementManager(inject(), inject()) }
    single { AchievementLibraryFactory() }
}