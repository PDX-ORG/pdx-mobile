package io.github.lexadiky.pdx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.navArgument
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsPage
import io.github.lexadiky.pdx.feature.news.NewsFeatureToggle
import io.github.lexadiky.pdx.feature.news.NewsFeedPage
import io.github.lexadiky.pdx.feature.pokemon.details.PokemonDetailsPage
import io.github.lexadiky.pdx.feature.pokemon.list.PokemonListPage
import io.github.lexadiky.pdx.feature.settings.SettingsPage
import io.github.lexadiky.pdx.feature.type.details.TypeDetailsPage
import io.github.lexadiky.pdx.feature.typechart.TypePage
import io.github.lexadiky.pdx.feature.whois.WhoIsPage
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.ifEnabled
import io.github.lexadiky.pdx.lib.navigation.PdxNavGraphBuilder

@Composable
fun routing(): PdxNavGraphBuilder.() -> Unit {
    val toggleManager = di.inject<FeatureToggleManager>()

    return remember(toggleManager) {
        {
            page("pdx://home") {}
            page("pdx://settings") { SettingsPage() }
            page("pdx://settings/achievements") { AchievementSettingsPage() }
            toggleManager.ifEnabled(NewsFeatureToggle) {
                page("pdx://news") { NewsFeedPage() }
            }
            page("pdx://pokemon") { PokemonListPage() }
            page("pdx://pokemon/{id}") {
                val id = argument(name = "id") { error("id required") }
                PokemonDetailsPage(pokemonId = id)
            }
            page("pdx://type") { TypePage() }
            modal("pdx://type/{id}") {
                val id = argument(name = "id") { error("id required") }
                TypeDetailsPage(typeId = id)
            }
            page("pdx://game/whois") { WhoIsPage() }
        }
    }
}
