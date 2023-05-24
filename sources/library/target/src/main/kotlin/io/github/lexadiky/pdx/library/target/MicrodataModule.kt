package io.github.lexadiky.pdx.library.target

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import io.github.lexadiky.pdx.library.target.platform.AndroidMicrodataManager

val MicrodataModule by module("library-microdata") {
    single<MicrodataManager> { AndroidMicrodataManager(inject()) }
}
