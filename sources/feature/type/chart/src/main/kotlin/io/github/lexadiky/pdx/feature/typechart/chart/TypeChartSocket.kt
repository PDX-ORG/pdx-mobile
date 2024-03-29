package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelationsUseCase
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.errorhandler.classify
import kotlinx.coroutines.launch

abstract class TypeChartSocket(initialState: TypeChartState) :
    ViewModelSocket<TypeChartState, TypeChartAction>(initialState)

internal class TypeChartSocketImpl(
    private val getPokemonDamageRelations: GetPokemonTypeDamageRelationsUseCase,
    private val navigator: Navigator
) : TypeChartSocket(TypeChartState()) {

    init {
        viewModelScope.launch {
            state = when (
                val data = getPokemonDamageRelations()
                    .classify(TypeChartSocketImpl::class)
            ) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> state.copy(
                    damageRelationsSubState = state.damageRelationsSubState.copy(
                        damageRelations = data.value
                    )
                )
            }
        }
    }

    override suspend fun onAction(action: TypeChartAction) {
        when (action) {
            is TypeChartAction.Navigate.TypeDetails -> openTypeDetails(action.type)
            is TypeChartAction.TypeClicked -> onTypeClicked(action.type)
            TypeChartAction.HideError -> hideError()
        }
    }

    private fun hideError() {
        state = state.copy(error = null)
    }

    private fun onTypeClicked(type: PokemonType) {
        state = state.toggleType(type)
    }

    private fun openTypeDetails(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }
}
