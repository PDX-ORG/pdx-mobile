package io.github.lexadiky.pdx.lib.arc

class StaticSocket<S, A>(state: S) : ViewModelSocket<S, A>(state) {

    override suspend fun onAction(action: A) = Unit // do nothing
}
