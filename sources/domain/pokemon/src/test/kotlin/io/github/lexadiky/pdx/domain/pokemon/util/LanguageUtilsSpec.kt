package io.github.lexadiky.pdx.domain.pokemon.util

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equals.shouldBeEqual
import java.util.Locale

class LanguageUtilsSpec : DescribeSpec({
    describe("asPokemonLanguage") {
        describe("via backward conversion") {
            describe("known languages") {
                PokemonLanguage.values().forAll { language ->
                    it("language '${language.languageTag}' should be convertible") {
                        val locale = Locale.forLanguageTag(language.languageTag)
                        locale.asPokemonLanguage() shouldBeEqual language
                    }
                }
            }
        }
    }
})
