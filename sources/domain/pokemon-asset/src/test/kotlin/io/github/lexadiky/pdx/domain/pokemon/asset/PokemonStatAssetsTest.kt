package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PokemonStatAssetsTest {

    @TestFactory
    fun `GIVEN domain stat THEN convert to asset`(): List<DynamicTest> {
        return PokemonStat.values().map { domainType ->
            val domainName = domainType.name
            val assetName = domainType.assets.name
            DynamicTest.dynamicTest("domain = $domainName, asset=$assetName") {
                Assertions.assertEquals(domainName, assetName)
            }
        }
    }
}
