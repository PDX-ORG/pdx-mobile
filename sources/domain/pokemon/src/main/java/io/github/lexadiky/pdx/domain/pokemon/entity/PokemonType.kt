package io.github.lexadiky.pdx.domain.pokemon.entity

import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.type.Type
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PokemonType(val id: String) {

    @SerialName("fire")
    FIRE("fire"),

    @SerialName("water")
    WATER("water"),

    @SerialName("grass")
    GRASS("grass"),

    @SerialName("ice")
    ICE("ice"),

    @SerialName("bug")
    BUG("bug"),

    @SerialName("steel")
    STEEL("steel"),

    @SerialName("dark")
    DARK("dark"),

    @SerialName("dragon")
    DRAGON("dragon"),

    @SerialName("normal")
    NORMAL("normal"),

    @SerialName("poison")
    POISON("poison"),

    @SerialName("ghost")
    GHOST("ghost"),

    @SerialName("flying")
    FLYING("flying"),

    @SerialName("electric")
    ELECTRIC("electric"),

    @SerialName("ground")
    GROUND("ground"),

    @SerialName("fairy")
    FAIRY("fairy"),

    @SerialName("fighting")
    FIGHTING("fighting"),

    @SerialName("psychic")
    PSYCHIC("psychic"),

    @SerialName("rock")
    ROCK("rock");
}

fun ResourcePointer<Type>.asType(): PokemonType {
    return PokemonType.values().first { it.id == this.name }
}