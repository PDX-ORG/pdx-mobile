package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PokemonStatAssetsSpec : DescribeSpec({
    describe("assets") {
        PokemonStat.values().forAll { stat ->
            it("GIVEN asset '${stat.name}' THEN asset name should match") {
                stat.assets.name shouldBe stat.name
            }
        }
    }
})
