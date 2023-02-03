package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider

val RoboFsModule by module("robo-fs") {
    single<FsManager> { RoboFsManager(inject()) }
    single<StaticResourceProvider> { inject<FsManager>().static() }
}