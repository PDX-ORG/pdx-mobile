package io.github.lexadiky.pdx.ui.uikit.resources

import android.content.Context
import android.text.format.DateFormat

class ResourceContext(private val androidContext: Context) {

    fun getStaticString(id: Int): String = androidContext.getString(id)

    fun getDateFormat() = DateFormat.getDateFormat(androidContext)
}