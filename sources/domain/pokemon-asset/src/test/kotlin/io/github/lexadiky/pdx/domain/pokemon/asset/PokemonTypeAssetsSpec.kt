package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PokemonTypeAssetsSpec : DescribeSpec({
    describe("assets") {
        PokemonType.values().forAll { type ->
            it("GIVEN asset '${type.name}' THEN asset name should match") {
                type.assets.name shouldBe type.name
            }
        }
    }
})
