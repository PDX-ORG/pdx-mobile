package io.github.lexadiky.pdx.domain.achievement.library

import io.github.lexadiky.pdx.domain.achievement.R
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

class WhoIsBeginnerAchievement : Achievement {

    override val id: String = "whois_beginner"
    override val name: StringResource = StringResource.from(R.string.achievement_lib_whois_beginner_name)
    override val description: StringResource = StringResource.from(R.string.achievement_lib_whois_beginner_description)
    override val icon: ImageResource? = null
}

class WhoIsTrainerAchievement : Achievement {

    override val id: String = "whois_trainer"
    override val name: StringResource = StringResource.from(R.string.achievement_lib_whois_trainer_name)
    override val description: StringResource = StringResource.from(R.string.achievement_lib_whois_trainer_description)
    override val icon: ImageResource? = null
}

class WhoIsChampionAchievement : Achievement {

    override val id: String = "whois_champion"
    override val name: StringResource = StringResource.from(R.string.achievement_lib_whois_champion_name)
    override val description: StringResource = StringResource.from(R.string.achievement_lib_whois_champion_description)
    override val icon: ImageResource? = null
}
