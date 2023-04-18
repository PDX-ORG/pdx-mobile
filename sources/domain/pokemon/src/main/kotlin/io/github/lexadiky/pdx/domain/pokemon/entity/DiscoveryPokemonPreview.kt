package io.github.lexadiky.pdx.domain.pokemon.entity

import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DiscoveryPokemonPreview(
    @SerialName("name")
    val name: String,
    @SerialName("national_dex_number")
    val nationalDexNumber: Int,
    @SerialName("local_names")
    val localNames: Map<PokemonLanguage, String>,
    @SerialName("normal_spite")
    val normalSprite: String?,
    @SerialName("shiny_sprite")
    val shinySprite: String?,
    @SerialName("types")
    val types: List<PokemonType>
) {
    private val simpleSearchIndex: String get() =
        (localNames.values + types.map { it.id }).joinToString(separator = "|", transform = { it.lowercase() })

    fun asDomain(localeManager: LocaleManager): PokemonPreview = PokemonPreview(
        name = name,
        localeName = extractName(localeManager),
        nationalDexNumber = nationalDexNumber,
        normalSprite = normalSprite,
        shinySprite = shinySprite ?: normalSprite,
        types = types,
        simpleSearchIndex = simpleSearchIndex
    )

    private fun extractName(localeManager: LocaleManager) =
        if (localeManager.settings.has(UseRomajiLocaleFlag)) {
            localNames[PokemonLanguage.JA_ROOMAJI]
        } else {
            localNames[localeManager.settings.system.asPokemonLanguage()]
        } ?: localNames.values.first()
}
