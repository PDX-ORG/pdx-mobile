package io.github.lexadiky.pdx.domain.pokemon.util

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.library.locale.LocaleManager
import io.github.lexadiky.pdx.library.locale.LocaleSettings
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equals.shouldBeEqual
import io.lexadiky.pokeapi.entity.common.Name
import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.mockk.every
import io.mockk.mockk
import java.util.Locale

class LanguageUtilsSpec : DescribeSpec({
    describe("asPokemonLanguage") {
        describe("via backward conversion") {
            PokemonLanguage.values().forAll { language ->
                it("language '${language.languageTag}' should be convertible") {
                    val locale = Locale.forLanguageTag(language.languageTag)
                    locale.asPokemonLanguage() shouldBeEqual language
                }
            }
        }
        it("unknown language") {
            Locale.forLanguageTag("th").asPokemonLanguage() shouldBeEqual PokemonLanguage.default()
        }
    }

    describe("ofCurrentLocale") {
        val localeManager = mockk<LocaleManager> {
            every { settings } returns LocaleSettings(
                system = Locale.ENGLISH,
                flags = emptyList()
            )
        }

        it("given empty list of names should return empty string") {
            emptyList<Name>().ofCurrentLocale(localeManager) shouldBeEqual ""
        }

        it("given list of names not containing matching locale return first available") {
            val names = listOf(
                Name("hello world", ResourcePointer("ja", "")),
                Name("hello world in zh", ResourcePointer("ko", ""))
            )
            names.ofCurrentLocale(localeManager) shouldBeEqual "hello world"
        }

        it("given list containing matching locale return matching string") {
            val names = listOf(
                Name("hello world in ja", ResourcePointer("ja", "")),
                Name("hello world", ResourcePointer("en", ""))
            )

            names.ofCurrentLocale(localeManager) shouldBeEqual "hello world"
        }
    }
})
