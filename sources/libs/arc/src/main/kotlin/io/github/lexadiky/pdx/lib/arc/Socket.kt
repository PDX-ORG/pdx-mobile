package io.github.lexadiky.pdx.lib.arc

interface Socket<State, Action> {

    val state: State

    fun act(action: Action)
}

operator fun <S> Socket<S, *>.component1(): S = state

operator fun <A> Socket<*, A>.component2(): (A) -> Unit = ::act
