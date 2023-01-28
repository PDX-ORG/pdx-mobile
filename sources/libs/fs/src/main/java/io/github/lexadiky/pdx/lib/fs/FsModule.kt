package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.akore.alice.module

val FsModule by module("fs") {
    single { FsManager(inject()) }
}