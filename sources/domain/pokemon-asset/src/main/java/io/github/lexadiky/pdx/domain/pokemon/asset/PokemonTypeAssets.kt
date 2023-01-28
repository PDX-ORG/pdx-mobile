package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.color.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.serialization.Serializable

@Serializable
enum class PokemonTypeAssets(
    private val nameResource: Int,
    private val colorArgb: Long
) {
    FIRE( R.string.domain_pokemon_type_fire, 0xFFEE8130),
    WATER( R.string.domain_pokemon_type_water, 0xFF6390F0),
    GRASS( R.string.domain_pokemon_type_grass, 0xFF7AC74C),
    ICE( R.string.domain_pokemon_type_ice, 0xFF96D9D6),
    BUG( R.string.domain_pokemon_type_bug, 0xFFA6B91A),
    STEEL( R.string.domain_pokemon_type_steel, 0xFFB7B7CE),
    DARK( R.string.domain_pokemon_type_dark, 0xFF705746),
    DRAGON( R.string.domain_pokemon_type_dragon, 0xFF6F35FC),
    NORMAL( R.string.domain_pokemon_type_normal, 0xFFA8A77A),
    POISON( R.string.domain_pokemon_type_poison, 0xFFA33EA1),
    GHOST( R.string.domain_pokemon_type_ghost, 0xFF735797),
    FLYING( R.string.domain_pokemon_type_flying, 0xFFA98FF3),
    ELECTRIC( R.string.domain_pokemon_type_electric, 0xFFF7D02C),
    GROUND( R.string.domain_pokemon_type_ground, 0xFFE2BF65),
    FAIRY( R.string.domain_pokemon_type_fairy, 0xFFD685AD),
    FIGHTING( R.string.domain_pokemon_type_fighting, 0xFFC22E28),
    PSYCHIC( R.string.domain_pokemon_type_psychic, 0xFFF95587),
    ROCK( R.string.domain_pokemon_type_rock, 0xFFB6A136);

    val title = StringResource.from(nameResource)
    val color = ColorResource.from(colorArgb)
}

val PokemonType.assets: PokemonTypeAssets get() = when (this) {
    PokemonType.FIRE -> PokemonTypeAssets.FIRE
    PokemonType.WATER -> PokemonTypeAssets.WATER
    PokemonType.GRASS -> PokemonTypeAssets.GRASS
    PokemonType.ICE -> PokemonTypeAssets.ICE
    PokemonType.BUG -> PokemonTypeAssets.BUG
    PokemonType.STEEL -> PokemonTypeAssets.STEEL
    PokemonType.DARK -> PokemonTypeAssets.DARK
    PokemonType.DRAGON -> PokemonTypeAssets.DRAGON
    PokemonType.NORMAL -> PokemonTypeAssets.NORMAL
    PokemonType.POISON -> PokemonTypeAssets.POISON
    PokemonType.GHOST -> PokemonTypeAssets.GHOST
    PokemonType.FLYING -> PokemonTypeAssets.FLYING
    PokemonType.ELECTRIC -> PokemonTypeAssets.ELECTRIC
    PokemonType.GROUND -> PokemonTypeAssets.GROUND
    PokemonType.FAIRY -> PokemonTypeAssets.FAIRY
    PokemonType.FIGHTING -> PokemonTypeAssets.FIGHTING
    PokemonType.PSYCHIC -> PokemonTypeAssets.PSYCHIC
    PokemonType.ROCK -> PokemonTypeAssets.ROCK
}
