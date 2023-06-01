package io.github.lexadiky.pdx.library.nibbler.decoration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel

@Composable
fun Decoration(route: String, content: @Composable () -> Unit) {
    val vm = di.viewModel<DecorationViewModel>()
    LaunchedEffect(route, content, vm) {
        vm.setContent(route, content)
    }
}

@Composable
fun DecorationHost(route: String, defaultContent: @Composable () -> Unit) {
    val vm = di.viewModel<DecorationViewModel>()
    LaunchedEffect(route, defaultContent) {
        vm.setDefaultContentForRoute(route, defaultContent)
    }

    val currentContent by vm.content(route).collectAsState({})
    currentContent()
}
