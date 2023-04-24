package io.github.lexadiky.pdx.feature.ability.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetAbilityUseCase
import io.github.lexadiky.pdx.lib.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class AbilityDetailsViewModel(
    private val id: String,
    private val getAbilityDetails: GetAbilityUseCase
) : ViewModel() {

    var state: AbilityDetailsState by mutableStateOf(AbilityDetailsState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getAbilityDetails(id, false).classify(this)) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> state.copy(
                    title = StringResource.from(data.value.nameLocale),
                    subtitle = StringResource.from(data.value.flavourText.lastOrNull()?.textLocale.orEmpty()),
                    effect = StringResource.from(data.value.effect?.textLocale.orEmpty())
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }
}
