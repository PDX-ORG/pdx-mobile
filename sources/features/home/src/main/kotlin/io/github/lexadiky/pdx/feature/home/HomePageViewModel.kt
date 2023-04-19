package io.github.lexadiky.pdx.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewSampleUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.feature.home.entitiy.HomeMenuItemType
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonType
import io.github.lexadiky.pdx.generated.analytics.HomeEventsSpec
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.lib.navigation.ShareIntentSender
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.ui.uikit.util.UikitStringFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class HomePageViewModel(
    private val navigator: Navigator,
    private val getPokemonPreview: GetPokemonPreviewSampleUseCase,
    private val getLatestViewedPokemonUseCase: GetLatestViewedPokemonUseCase,
    private val eventSpec: HomeEventsSpec,
    private val shareIntentSender: ShareIntentSender
) : ViewModel() {

    var state by mutableStateOf(HomePageState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPreview(FEATURED_POKEMON_SAMPLE_SIZE)) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(
                    featuredPokemon = makeFeaturedPokemon(data.value)
                )
            }

        }
        viewModelScope.launch {
            navigator.currentRoute.map {
                when (val data = getLatestViewedPokemonUseCase(FEATURED_POKEMON_SAMPLE_SIZE)) {
                    is Either.Left -> state.copy(error = UIError.generic())
                    is Either.Right -> state.copy(
                        latestViewedPokemon = makeFeaturedPokemon(data.value)
                    )
                }
            }.collectLatest {
                state = it
            }
        }
    }

    fun openPokemonList() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.POKEMON_LIST.tag)
        navigator.navigate("pdx://pokemon")
    }

    fun openWhoIs() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.WHO_IS.tag)
        navigator.navigate("pdx://game/whois")
    }

    fun openNews() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.NEWS.tag)
        navigator.navigate("pdx://news")
    }

    fun openAchievements() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.ACHIEVEMENTS.tag)
        navigator.navigate("pdx://settings/achievements")
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun openPokemonDetails(item: SuggestedPokemonItem, type: SuggestedPokemonType) = viewModelScope.launch {
        eventSpec.onSuggestedPokemonClicked(item.id, type.tag)
        navigator.navigate("pdx://pokemon/${item.id}")
    }

    private fun makeFeaturedPokemon(pokemon: List<PokemonPreview>): List<SuggestedPokemonItem> {
        return pokemon.map {
            SuggestedPokemonItem(
                id = it.name,
                name = StringResource.from(it.localeName),
                image = it.normalSprite?.let { ImageResource.from(it) }
                    ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
                nationalDexId = UikitStringFormatter.nationalId(it.nationalDexNumber),
            )
        }
    }

    fun openApplicationShare() {
        shareIntentSender.shareApplication()
    }

    companion object {

        private const val FEATURED_POKEMON_SAMPLE_SIZE = 3
    }
}
