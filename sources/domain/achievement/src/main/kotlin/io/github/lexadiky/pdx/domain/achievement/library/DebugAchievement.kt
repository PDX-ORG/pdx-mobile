package io.github.lexadiky.pdx.domain.achievement.library

import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

class DebugAchievement : Achievement {
    override val id: String = "__debug__"
    override val name: StringResource = StringResource.from("DEBUG")
    override val description: StringResource = StringResource.from("Achievement for debug")
    override val icon: ImageResource? = null
}
