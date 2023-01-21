package io.github.lexadiky.pdx.ui.uikit.util.scroll

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalPrimeScrollState = compositionLocalOf { PrimeScrollState.inactive() }

@Composable
fun ProvideLocalPrimeScrollState(content: @Composable () -> Unit) {
    val primeScrollState = rememberPrimeScrollState()
    CompositionLocalProvider(LocalPrimeScrollState provides primeScrollState) {
        content()
    }
}
