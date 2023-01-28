package io.github.lexadiky.pdx.lib.target

import android.content.Context
import io.github.lexadiky.akore.alice.eagerModule

internal fun ApplicationModule(context: Context) = eagerModule("application") {
    single { context }
}