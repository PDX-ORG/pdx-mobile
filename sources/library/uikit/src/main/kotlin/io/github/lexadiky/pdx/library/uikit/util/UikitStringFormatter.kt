package io.github.lexadiky.pdx.library.uikit.util

import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.format
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitString

object UikitStringFormatter {

    fun nationalId(id: Int): StringResource =
        StringResource.from(UikitString.uikit_national_id_template)
            .format(id)
}
