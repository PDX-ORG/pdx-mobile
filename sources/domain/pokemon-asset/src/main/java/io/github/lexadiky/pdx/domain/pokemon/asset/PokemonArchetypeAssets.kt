package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

enum class PokemonArchetypeAssets(
    private val titleResource: Int
) {
    SpecialAttacker(R.string.domain_pokemon_asset_archetype_sp_attacker),
    PhysicalAttacker(R.string.domain_pokemon_asset_archetype_ph_attacker),
    Speedster(R.string.domain_pokemon_asset_archetype_speedster),
    Unknown(R.string.domain_pokemon_asset_archetype_unknown);

    val title get() = StringResource.from(titleResource)
}

val PokemonArchetype.assets: PokemonArchetypeAssets
    get() = when (this) {
        PokemonArchetype.SpecialAttacker -> PokemonArchetypeAssets.SpecialAttacker
        PokemonArchetype.PhysicalAttacker -> PokemonArchetypeAssets.PhysicalAttacker
        PokemonArchetype.Speedster -> PokemonArchetypeAssets.Speedster
        PokemonArchetype.Unknown -> PokemonArchetypeAssets.Unknown
    }