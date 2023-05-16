package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonSprites(
    val default: String?,
    val defaultShiny: String?,
    val all: List<String>
)

enum class SpriteTag {
    UNKNOWN
}
