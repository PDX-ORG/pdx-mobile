package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.lib.fs.statist.BundleStaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.RemoteConfigResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.RoboStaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

val RoboFsModule by module("robo-fs") {
    single<FsManager> { RoboFsManager(inject()) }
    single { inject<FsManager>().static() }
    single { RoboStaticResourceProvider(inject(), inject()) }
    single { BundleStaticResourceProvider(inject()) }
    single { RemoteConfigResourceProvider(inject(), inject()) }
}
