package io.github.lexadiky.pdx.domain.pokemon.entity

import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.stat.Stat

enum class PokemonStat {
    HP, Speed, Attack, SpAttack, Defence, SpDefence
}

fun ResourcePointer<Stat>.asPokemonStat(): PokemonStat = when(name) {
    "hp" -> PokemonStat.HP
    "speed" -> PokemonStat.Speed
    "attack" -> PokemonStat.Attack
    "special-attack" -> PokemonStat.SpAttack
    "defense" -> PokemonStat.Defence
    "special-defense" -> PokemonStat.SpDefence
    else -> error("unknown stat: $name")
}