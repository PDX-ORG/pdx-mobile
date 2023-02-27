package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean,
    val nameLocale: String,
    val effect: Effect?,
    val flavourText: List<Flavour>
) {

    class Effect(
        val textLocale: String,
        val shortTextLocale: String
    )

    class Flavour(
        val textLocale: String
    )
}