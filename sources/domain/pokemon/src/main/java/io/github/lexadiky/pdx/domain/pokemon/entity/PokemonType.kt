package io.github.lexadiky.pdx.domain.pokemon.entity

import androidx.compose.ui.graphics.Color
import io.github.lexadiky.pdx.domain.pokemon.R
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.color.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PokemonType(
    val id: String,
    private val nameResource: Int,
    private val colorArgb: Long
) {

    @SerialName("fire")
    FIRE("fire", R.string.domain_pokemon_type_fire, 0xFFEE8130),

    @SerialName("water")
    WATER("water", R.string.domain_pokemon_type_water, 0xFF6390F0),

    @SerialName("grass")
    GRASS("grass", R.string.domain_pokemon_type_grass, 0xFF7AC74C),

    @SerialName("ice")
    ICE("ice", R.string.domain_pokemon_type_ice, 0xFF96D9D6),

    @SerialName("bug")
    BUG("bug", R.string.domain_pokemon_type_bug, 0xFFA6B91A),

    @SerialName("steel")
    STEEL("steel", R.string.domain_pokemon_type_steel, 0xFFB7B7CE),

    @SerialName("dark")
    DARK("dark", R.string.domain_pokemon_type_dark, 0xFF705746),

    @SerialName("dragon")
    DRAGON("dragon", R.string.domain_pokemon_type_dragon, 0xFF6F35FC),

    @SerialName("normal")
    NORMAL("normal", R.string.domain_pokemon_type_normal, 0xFFA8A77A),

    @SerialName("poison")
    POISON("poison", R.string.domain_pokemon_type_poison, 0xFFA33EA1),

    @SerialName("ghost")
    GHOST("ghost", R.string.domain_pokemon_type_ghost, 0xFF735797),

    @SerialName("flying")
    FLYING("flying", R.string.domain_pokemon_type_flying, 0xFFA98FF3),

    @SerialName("electric")
    ELECTRIC("electric", R.string.domain_pokemon_type_electric, 0xFFF7D02C),

    @SerialName("ground")
    GROUND("ground", R.string.domain_pokemon_type_ground, 0xFFE2BF65),

    @SerialName("fairy")
    FAIRY("fairy", R.string.domain_pokemon_type_fairy, 0xFFD685AD),

    @SerialName("fighting")
    FIGHTING("fighting", R.string.domain_pokemon_type_fighting, 0xFFC22E28),

    @SerialName("psychic")
    PSYCHIC("psychic", R.string.domain_pokemon_type_psychic, 0xFFF95587),

    @SerialName("rock")
    ROCK("rock", R.string.domain_pokemon_type_rock, 0xFFB6A136);

    fun toStringResource(): StringResource =
        StringResource.from(nameResource)

    fun toColorResource(): ColorResource =
        ColorResource.from(Color(colorArgb))
}