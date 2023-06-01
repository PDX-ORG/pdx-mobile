package io.github.lexadiky.pdx.feature.home

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPreviewSampleUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.feature.home.entitiy.HomeMenuItemType
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonType
import io.github.lexadiky.pdx.generated.analytics.HomeEventsSpec
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.android.util.ShareIntentSender
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.util.UikitStringFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class HomePageSocket(initialState: HomePageState) :
    ViewModelSocket<HomePageState, HomePageAction>(initialState)

class HomePageSocketImpl(
    private val navigator: Navigator,
    private val getPokemonPreview: GetPokemonPreviewSampleUseCase,
    private val getLatestViewedPokemonUseCase: GetLatestViewedPokemonUseCase,
    private val eventSpec: HomeEventsSpec,
    private val shareIntentSender: ShareIntentSender
) : HomePageSocket(HomePageState()) {

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

    override suspend fun onAction(action: HomePageAction) {
        when (action) {
            HomePageAction.HideError -> hideError()
            HomePageAction.Navigate.Achievements -> openAchievements()
            HomePageAction.Navigate.ApplicationShare -> openApplicationShare()
            HomePageAction.Navigate.News -> openNews()
            is HomePageAction.Navigate.PokemonDetails -> openPokemonDetails(action.item, action.type)
            HomePageAction.Navigate.PokemonList -> openPokemonList()
            HomePageAction.Navigate.WhoIs -> openWhoIs()
            HomePageAction.Navigate.Types -> openTypes()
        }
    }

    private fun openTypes() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.TYPES.tag)
        navigator.navigate("pdx://type")
    }

    private fun openPokemonList() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.POKEMON_LIST.tag)
        navigator.navigate("pdx://pokemon")
    }

    private fun openWhoIs() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.WHO_IS.tag)
        navigator.navigate("pdx://game/whois")
    }

    private fun openNews() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.NEWS.tag)
        navigator.navigate("pdx://news")
    }

    private fun openAchievements() = viewModelScope.launch {
        eventSpec.onMenuClicked(HomeMenuItemType.ACHIEVEMENTS.tag)
        navigator.navigate("pdx://settings/achievements")
    }

    private fun hideError() {
        state = state.copy(error = null)
    }

    private fun openPokemonDetails(item: SuggestedPokemonItem, type: SuggestedPokemonType) = viewModelScope.launch {
        eventSpec.onSuggestedPokemonClicked(item.id, type.tag)
        navigator.navigate("pdx://pokemon/${item.id}")
    }

    private fun openApplicationShare() {
        shareIntentSender.shareApplication()
    }

    private fun makeFeaturedPokemon(pokemon: List<PokemonPreview>): List<SuggestedPokemonItem> {
        return pokemon.map {
            SuggestedPokemonItem(
                id = it.name,
                name = StringResource.from(it.localeName),
                image = it.normalSprite?.let { ImageResource.from(it) }
                    ?: ImageResource.from(UikitDrawable.uikit_ic_pokeball),
                nationalDexId = UikitStringFormatter.nationalId(it.nationalDexNumber),
            )
        }
    }

    companion object {

        private const val FEATURED_POKEMON_SAMPLE_SIZE = 3
    }
}
