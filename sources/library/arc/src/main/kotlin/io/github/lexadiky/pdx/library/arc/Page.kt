package io.github.lexadiky.pdx.library.arc

import androidx.compose.runtime.Composable

@Composable
fun <S, A> Page(
    socket: Socket<S, A>,
    content: @Composable (S, (A) -> Unit) -> Unit,
) {
    val (state, act) = socket
    content(state, act)
}
