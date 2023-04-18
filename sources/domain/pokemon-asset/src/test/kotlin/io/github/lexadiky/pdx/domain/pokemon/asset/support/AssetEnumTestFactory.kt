package io.github.lexadiky.pdx.domain.pokemon.asset.support

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest

object AssetEnumTestFactory {

    fun <T : Enum<*>, V : Enum<*>> assetConversion(
        values: Array<T>,
        extractor: (T) -> V,
    ): List<DynamicTest> {
        return values.map { domainType ->
            val domainName = domainType.name
            val assetName = extractor(domainType).name
            DynamicTest.dynamicTest("domain = $domainName, asset=$assetName") {
                Assertions.assertEquals(domainName, assetName)
            }
        }
    }
}
