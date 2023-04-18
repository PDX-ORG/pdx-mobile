package io.github.lexadiky.pdx.domain.achievement

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val AchievementModule by module("achievement") {
    single { AchievementManager(inject(), inject()) }
    single { AchievementLibraryFactory() }
}
