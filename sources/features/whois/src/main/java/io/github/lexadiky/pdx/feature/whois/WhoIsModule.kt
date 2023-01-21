package io.github.lexadiky.pdx.feature.whois

import io.github.lexadiky.pdx.lib.arc.di.module

val WhoIsModule by module("who-is") {
    viewModel { WhoIsViewModel(inject()) }
}