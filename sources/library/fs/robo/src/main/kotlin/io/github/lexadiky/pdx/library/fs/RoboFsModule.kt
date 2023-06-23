package io.github.lexadiky.pdx.library.fs

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.fs.statist.RemoteConfigResourceProvider
import io.github.lexadiky.pdx.library.fs.statist.RoboStaticResourceProvider

val RoboFsModule by module("robo-fs") {
    single<FsManager> { RoboFsManager(inject()) }
    single { inject<FsManager>().static() }
    single { RoboStaticResourceProvider(inject()) }
    single { RemoteConfigResourceProvider(inject(), inject()) }
}
