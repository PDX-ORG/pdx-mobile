package io.github.lexadiky.pdx.domain.pokemon.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.language.Language

class PokemonLanguageSpec : DescribeSpec({
    describe("PokemonLanguage.default") {
        it("is english") {
            PokemonLanguage.default() shouldBeEqual PokemonLanguage.ENGLISH
        }
    }

    describe("ResourcePointer<Language>.asLanguage()") {
        it("given known pointer should provide correct language") {
            val pointer = ResourcePointer<Language>(PokemonLanguage.FRENCH.languageTag, "some_url_does not matter")
            pointer.asLanguage() shouldBeEqual PokemonLanguage.FRENCH
        }
        it("given unknown pointer should provide default language") {
            val pointer = ResourcePointer<Language>("th", "some_url_does not matter")
            pointer.asLanguage() shouldBeEqual PokemonLanguage.default()
        }
        it("given known pointer without url should provide correct language") {
            val pointer = ResourcePointer<Language>(PokemonLanguage.FRENCH.languageTag, "some_url_does not matter")
            pointer.asLanguage() shouldBeEqual PokemonLanguage.FRENCH
        }
    }
})
