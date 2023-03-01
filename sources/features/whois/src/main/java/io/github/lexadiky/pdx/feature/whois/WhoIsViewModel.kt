package io.github.lexadiky.pdx.feature.whois

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.achievement.AchievementManager
import io.github.lexadiky.pdx.domain.achievement.library.WhoIsBeginnerAchievement
import io.github.lexadiky.pdx.domain.achievement.library.WhoIsChampionAchievement
import io.github.lexadiky.pdx.domain.achievement.library.WhoIsTrainerAchievement
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.feature.whois.entity.WhoIsPokemonVariant
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.fs.FsManager
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

internal class WhoIsViewModel(
    private val getPokemonPreviewUseCase: GetPokemonPreviewUseCase,
    private val achievementManager: AchievementManager,
    fsManager: FsManager
) : ViewModel() {

    private var streakFsState by fsManager.atomic("whois")
        .int("streak", 0)

    var state by mutableStateOf(WhoIsState(streak = streakFsState))
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPreviewUseCase()) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> state.copy(allPokemon = data.value.toVariants())
            }.reshuffleNew()
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun onAnswer(answer: WhoIsPokemonVariant) {
        if (!state.masked) {
            return
        }
        viewModelScope.launch {
            val newStreak = if (answer == state.whoisTarget) state.streak + 1 else 0
            recordNewStreak(newStreak)
            state = state.copy(
                masked = false,
                streak = newStreak
            )
            delay(GUESS_RESULT_LEN)
            state = state
                .copy(masked = true)
                .reshuffleNew()
        }
    }

    private fun recordNewStreak(newStreak: Int) {
        streakFsState = newStreak
        when {
            newStreak >= ACHIEVEMENT_THRESHOLD_CHAMPION -> achievementManager.give(WhoIsChampionAchievement())
            newStreak >= ACHIEVEMENT_THRESHOLD_TRAINER -> achievementManager.give(WhoIsTrainerAchievement())
            newStreak >= ACHIEVEMENT_THRESHOLD_BEGINNER -> achievementManager.give(WhoIsBeginnerAchievement())
        }
    }

    private fun List<PokemonPreview>.toVariants(): List<WhoIsPokemonVariant> {
        return filter { it.normalSprite != null }.map { preview ->
            WhoIsPokemonVariant(
                image = ImageResource.from(preview.normalSprite!!),
                name = StringResource.from(preview.localeName),
                color = preview.types.first().assets.color,
                id = preview.name
            )
        }
    }

    companion object {

        private val GUESS_RESULT_LEN = 3.seconds

        private const val ACHIEVEMENT_THRESHOLD_BEGINNER = 3
        private const val ACHIEVEMENT_THRESHOLD_TRAINER = 10
        private const val ACHIEVEMENT_THRESHOLD_CHAMPION = 100
    }
}
