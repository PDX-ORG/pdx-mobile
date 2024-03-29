package io.github.lexadiky.pdx.library.uikit.resources

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import io.github.lexadiky.pdx.library.resources.string.FormattedStringResource
import io.github.lexadiky.pdx.library.resources.string.LiteralStringResource
import io.github.lexadiky.pdx.library.resources.string.PlaceholderStringResource
import io.github.lexadiky.pdx.library.resources.string.ResStringResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.TemporalStringResource
import io.github.lexadiky.pdx.library.uikit.UikitString
import java.util.Date

@Composable
fun StringResource.render(): String {
    val rendered by renderAsState()
    return rendered
}

fun StringResource.render(context: Context): String {
    return when (this) {
        is FormattedStringResource -> this.base.render(context)
            .formatWithResources(this.arguments, context)

        is LiteralStringResource -> this.value
        is ResStringResource -> context.getString(this.stringRes)
        is TemporalStringResource -> DateFormat.getDateFormat(context)
            .format(Date(instant.epochSeconds * 1000))

        PlaceholderStringResource -> context.getString(UikitString.uikit_string_resource_placeholder)
    }
}

@Composable
private fun StringResource.renderAsState(): State<String> {
    val context = LocalContext.current
    return remember(this, context) {
        mutableStateOf(render(context))
    }
}

@Suppress("UNCHECKED_CAST")
private fun String.formatWithResources(arguments: Array<out Any>, context: Context): String {
    arguments.indices.forEach { idx ->
        val arg = arguments[idx]
        if (arg is StringResource) {
            (arguments as Array<Any>)[idx] = arg.render(context)
        }
    }

    return format(*arguments)
}
