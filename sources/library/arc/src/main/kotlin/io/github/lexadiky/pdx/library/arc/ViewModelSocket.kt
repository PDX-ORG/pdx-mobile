package io.github.lexadiky.pdx.library.arc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class ViewModelSocket<S, A>(initialState: S) : Socket<S, A>, ViewModel() {
    var isAsync: Boolean = true

    override var state: S by mutableStateOf(initialState)
        protected set

    final override fun act(action: A) {
        if (isAsync) {
            viewModelScope.launch(SupervisorJob()) {
                onAction(action)
            }
        } else {
            runBlocking { onAction(action) }
        }
    }

    protected abstract suspend fun onAction(action: A)

    fun synchronize() = apply {
        isAsync = false
    }
}
