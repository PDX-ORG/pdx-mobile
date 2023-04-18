package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.asset.support.AssetEnumTestFactory
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PokemonStatAssetsTest {

    @TestFactory
    fun `GIVEN domain stat THEN convert to asset`(): List<DynamicTest> {
        return AssetEnumTestFactory.assetConversion(PokemonStat.values()) { it.assets }
    }
}
