package io.github.lexadiky.pdx.domain.achievement

import io.github.lexadiky.pdx.domain.achievement.library.Achievement
import io.github.lexadiky.pdx.domain.achievement.library.ShinyShakeAchievement
import io.github.lexadiky.pdx.domain.achievement.library.WhoIsBeginnerAchievement

class AchievementLibraryFactory {

    fun list(): List<Achievement> = listOf(
        ShinyShakeAchievement(),
        WhoIsBeginnerAchievement()
    )
}