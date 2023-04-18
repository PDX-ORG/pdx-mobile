package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.asset.support.AssetEnumTestFactory
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PokemonArchetypeAssetsTest {

    @TestFactory
    fun `GIVEN domain archetype THEN convert to asset`(): List<DynamicTest> {
        return AssetEnumTestFactory.assetConversion(PokemonArchetype.values()) { it.assets }
    }
}