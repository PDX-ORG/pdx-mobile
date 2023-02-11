package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

internal class ToolbarViewModel(
    private val connector: ToolbarConnector
) : ViewModel() {

    var state: ToolbarState by mutableStateOf(ToolbarState())
        private set

    suspend fun openDrawer() {
        connector.onDrawerToggled()
    }

    sealed interface Event {

        object OpenDrawer : Event
    }
}
