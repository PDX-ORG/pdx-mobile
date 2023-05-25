@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.uikit.util.scroll

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

class PrimeScrollState(
    private val lazyListState: LazyListState
) {
    fun asLazyListState(): LazyListState = lazyListState

    companion object
}

@Composable
fun rememberPrimeScrollState(): PrimeScrollState {
    val scrollColumnState = rememberLazyListState()
    return PrimeScrollState(
        lazyListState = scrollColumnState
    )
}

fun PrimeScrollState.Companion.inactive(): PrimeScrollState {
    val scrollState = LazyListState()
    return PrimeScrollState(
        lazyListState = scrollState
    )
}

@Composable
fun PrimeScrollState.asTopAppBarState(): TopAppBarState {
    val topAppBarState = rememberTopAppBarState()
    val firstVisibleItemScrollOffset by remember {
        derivedStateOf { -asLazyListState().firstVisibleItemScrollOffset.toFloat() }
    }
    topAppBarState.contentOffset = firstVisibleItemScrollOffset
    return topAppBarState
}
