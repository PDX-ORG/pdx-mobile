package io.github.lexadiky.pdx.feature.whois

import io.github.lexadiky.pdx.feature.whois.entity.WhoIsPokemonVariant
import io.github.lexadiky.pdx.lib.errorhandler.UIError

data class WhoIsState(
    val error: UIError? = null,
    val allPokemon: List<WhoIsPokemonVariant> = emptyList(),
    val currentVariants: List<WhoIsPokemonVariant> = emptyList(),
    val whoisTarget: WhoIsPokemonVariant? = null,
    val masked: Boolean = true,
    val streak: Int = 0
) {
    val isLoading = allPokemon.isEmpty()

    fun reshuffleNew(): WhoIsState {
        val currentVariantsSelection = allPokemon.shuffled()
            .take(VARIANTS_SIZE)

        return copy(
            currentVariants = currentVariantsSelection,
            whoisTarget = currentVariantsSelection.randomOrNull()
        )
    }

    companion object {

        private const val VARIANTS_SIZE = 3
    }
}
