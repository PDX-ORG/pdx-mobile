package io.github.lexadiky.pdx.library.target

import android.content.Context
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.core.resource.ResourceReader

internal fun ApplicationModule(context: Context) = eagerModule("application") {
    single { context }
    single { ResourceReader(inject()) }
}
