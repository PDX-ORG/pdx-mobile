package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemonasset.R
import io.github.lexadiky.pdx.library.resources.color.ColorResource
import io.github.lexadiky.pdx.library.resources.color.from
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

@Suppress("MagicNumber")
enum class PokemonTypeAssets(
    nameResource: Int,
    colorArgb: Long,
    iconResource: Int
) {
    FIRE(R.string.domain_pokemon_type_fire, 0xFFEE8130, R.drawable.domain_pokemon_ic_type_fire),
    WATER(R.string.domain_pokemon_type_water, 0xFF6390F0, R.drawable.domain_pokemon_ic_type_water),
    GRASS(R.string.domain_pokemon_type_grass, 0xFF7AC74C, R.drawable.domain_pokemon_ic_type_grass),
    ICE(R.string.domain_pokemon_type_ice, 0xFF96D9D6, R.drawable.domain_pokemon_ic_type_ice),
    BUG(R.string.domain_pokemon_type_bug, 0xFFA6B91A, R.drawable.domain_pokemon_ic_type_bug),
    STEEL(R.string.domain_pokemon_type_steel, 0xFFB7B7CE, R.drawable.domain_pokemon_ic_type_steel),
    DARK(R.string.domain_pokemon_type_dark, 0xFF705746, R.drawable.domain_pokemon_ic_type_dark),
    DRAGON(R.string.domain_pokemon_type_dragon, 0xFF6F35FC, R.drawable.domain_pokemon_ic_type_dragon),
    NORMAL(R.string.domain_pokemon_type_normal, 0xFFA8A77A, R.drawable.domain_pokemon_ic_type_normal),
    POISON(R.string.domain_pokemon_type_poison, 0xFFA33EA1, R.drawable.domain_pokemon_ic_type_poison),
    GHOST(R.string.domain_pokemon_type_ghost, 0xFF735797, R.drawable.domain_pokemon_ic_type_ghost),
    FLYING(R.string.domain_pokemon_type_flying, 0xFFA98FF3, R.drawable.domain_pokemon_ic_type_flying),
    ELECTRIC(R.string.domain_pokemon_type_electric, 0xFFF7D02C, R.drawable.domain_pokemon_ic_type_electric),
    GROUND(R.string.domain_pokemon_type_ground, 0xFFE2BF65, R.drawable.domain_pokemon_ic_type_ground),
    FAIRY(R.string.domain_pokemon_type_fairy, 0xFFD685AD, R.drawable.domain_pokemon_ic_type_fairy),
    FIGHTING(R.string.domain_pokemon_type_fighting, 0xFFC22E28, R.drawable.domain_pokemon_ic_type_fighting),
    PSYCHIC(R.string.domain_pokemon_type_psychic, 0xFFF95587, R.drawable.domain_pokemon_ic_type_psychic),
    ROCK(R.string.domain_pokemon_type_rock, 0xFFB6A136, R.drawable.domain_pokemon_ic_type_rock);

    val title = StringResource.from(nameResource)
    val color = ColorResource.from(colorArgb)
    val icon = ImageResource.from(iconResource)
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
