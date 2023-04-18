package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.asset.support.AssetEnumTestFactory
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PokemonTypeAssetsTest {

    @TestFactory
    fun `GIVEN domain type THEN convert to asset`(): List<DynamicTest> {
        return AssetEnumTestFactory.assetConversion( PokemonType.values()) { it.assets }
    }
}
