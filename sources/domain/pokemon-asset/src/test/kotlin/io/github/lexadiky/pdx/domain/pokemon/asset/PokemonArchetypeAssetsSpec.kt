package io.github.lexadiky.pdx.domain.pokemon.asset

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PokemonArchetypeAssetsSpec : DescribeSpec({
    describe("assets") {
        PokemonArchetype.values().forAll { archetype ->
            it("GIVEN asset '${archetype.name}' THEN asset name should match") {
                archetype.assets.name shouldBe archetype.name
            }
        }
    }
})
