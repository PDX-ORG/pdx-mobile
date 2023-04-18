package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

enum class PokemonStatAssets(
    shortTitleResource: Int
) {
    HP(R.string.domain_pokemon_asset_stat_short_hp),
    Speed(R.string.domain_pokemon_asset_stat_short_speed),
    Attack(R.string.domain_pokemon_asset_stat_short_attack),
    SpAttack(R.string.domain_pokemon_asset_stat_short_sp_attack),
    Defence(R.string.domain_pokemon_asset_stat_short_defence),
    SpDefence(R.string.domain_pokemon_asset_stat_short_sp_defence);

    val shortTitle: StringResource = StringResource.from(shortTitleResource)
}

val PokemonStat.assets: PokemonStatAssets get() = when (this) {
    PokemonStat.HP -> PokemonStatAssets.HP
    PokemonStat.Speed -> PokemonStatAssets.Speed
    PokemonStat.Attack -> PokemonStatAssets.Attack
    PokemonStat.SpAttack -> PokemonStatAssets.SpAttack
    PokemonStat.Defence -> PokemonStatAssets.Defence
    PokemonStat.SpDefence -> PokemonStatAssets.SpDefence
}
