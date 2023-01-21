package io.github.lexadiky.pdx.lib.target

import android.content.Context
import io.github.lexadiky.pdx.lib.arc.di.eagerModule

internal fun ApplicationModule(context: Context) = eagerModule("application") {
    single { context }
}