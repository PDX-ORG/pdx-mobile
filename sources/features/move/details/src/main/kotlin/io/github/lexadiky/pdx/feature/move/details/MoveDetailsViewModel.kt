package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetMoveDetails
import io.github.lexadiky.pdx.feature.move.details.entity.attribute.MoveAttribute
import io.github.lexadiky.pdx.lib.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class MoveDetailsViewModel(
    private val moveId: String,
    private val getMoveDetails: GetMoveDetails,
    private val navigator: Navigator,
) : ViewModel() {

    var state by mutableStateOf(MoveDetailsState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getMoveDetails(moveId).classify(MoveDetailsViewModel::class)) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> moveDetailsState(data.value)
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun onTypeClicked(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }

    private fun moveDetailsState(move: PokemonMove) = state.copy(
        localeName = StringResource.from(move.localeName),
        localeFlavourText = move.localeFlavourText?.let { StringResource.from(it) },
        localeShortEffect = move.localeShortEffect?.let { StringResource.from(it) },
        type = move.type,
        pp = move.pp,
        attributes = extractAttributes(move)
    )

    private fun extractAttributes(move: PokemonMove): List<MoveAttribute> = buildList {
        move.power?.let { power ->
            add(
                MoveAttribute.Text(
                    icon = ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_swords),
                    title = StringResource.from(R.string.move_details_attr_power),
                    value = StringResource.from(power.toString())
                )
            )
        }

        move.accuracy?.let { accuracy ->
            add(
                MoveAttribute.Text(
                    icon = ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_accuracy),
                    title = StringResource.from(R.string.move_details_attr_accuracy),
                    value = StringResource.from(accuracy.toString())
                )
            )
        }

        move.pp?.let { pp ->
            add(
                MoveAttribute.Text(
                    icon = ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_speed),
                    title = StringResource.from(R.string.move_details_attr_pp),
                    value = StringResource.from(pp.toString())
                )
            )
        }

        move.effectChance?.let { effectChance ->
            add(
                MoveAttribute.Text(
                    icon = ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_effect),
                    title = StringResource.from(R.string.move_details_attr_effect_chance),
                    value = StringResource.from(effectChance.toString())
                )
            )
        }

        move.type.let { type ->
            add(
                MoveAttribute.Text(
                    icon = PokemonType.BUG.assets.icon,
                    title = StringResource.from(R.string.move_details_attr_type),
                    value = type.assets.title
                )
            )
        }
    }
}
