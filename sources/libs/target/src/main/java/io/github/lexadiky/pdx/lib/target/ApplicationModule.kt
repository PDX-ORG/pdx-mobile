package io.github.lexadiky.pdx.lib.target

import android.content.Context
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single

internal fun ApplicationModule(context: Context) = eagerModule("application") {
    single { context }
}
