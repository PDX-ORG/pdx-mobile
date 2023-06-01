package io.github.lexadiky.pdx.library.dynbanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.library.dynbanner.domain.DynamicBannerRepository
import io.github.lexadiky.pdx.library.dynbanner.entity.Banner
import io.github.lexadiky.pdx.library.dynbanner.entity.BannerAction
import io.github.lexadiky.pdx.library.dynbanner.entity.BannerType
import io.github.lexadiky.pdx.library.errorhandler.classify
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import kotlinx.coroutines.launch

internal class DynamicBannerViewModel(
    private val bannerId: String,
    private val isPlaceholderEnabled: Boolean,
    private val repository: DynamicBannerRepository,
    private val navigator: Navigator
) : ViewModel() {

    var banner: Banner? by mutableStateOf(createPlaceholderBanner())
        private set

    init {
        viewModelScope.launch {
            banner = repository.fetch(bannerId)
                .classify(DynamicBannerViewModel::class)
                .getOrNull()
        }
    }

    fun onAction() = viewModelScope.launch {
        val action = banner?.action
        when (action?.type) {
            BannerAction.Type.LinkTo -> navigator.navigate(action.route)
            null -> Unit
        }
    }

    private fun createPlaceholderBanner(): Banner? {
        if (!isPlaceholderEnabled) return null
        return Banner(BannerType.NativeMessage, "", null, null)
    }
}
