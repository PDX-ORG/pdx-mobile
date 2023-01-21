package io.github.lexadiky.pdx.lib.fs

import io.github.lexadiky.pdx.lib.arc.di.module

val FsModule by module("fs") {
    single { FsManager(inject()) }
}