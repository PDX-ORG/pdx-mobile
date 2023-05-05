package io.github.lexadiky.pdx.lib.arc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class ViewModelSocket<S, A>(initialState: S) : Socket<S, A>, ViewModel() {

    override var state: S by mutableStateOf(initialState)
        protected set

    init {
        viewModelScope.launch { initialize() }
    }
    final override fun act(action: A) {
        viewModelScope.launch(Dispatchers.Default + Job()) { onAction(action) }
    }

    open suspend fun initialize() = Unit

    protected abstract suspend fun onAction(action: A)
}
