package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.akore.alice.module

val RoboFsModule by module("robo-fs") {
    single<FsManager> { RoboFsManager(inject()) }
}