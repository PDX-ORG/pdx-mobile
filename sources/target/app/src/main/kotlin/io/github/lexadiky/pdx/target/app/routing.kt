package io.github.lexadiky.pdx.target.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.lechuck.robo.NaviNavGraphBuilder
import io.github.lexadiky.pdx.feature.ability.details.AbilityDetailsPage
import io.github.lexadiky.pdx.feature.debugpanel.DebugPanelFeature
import io.github.lexadiky.pdx.feature.home.HomePage
import io.github.lexadiky.pdx.feature.move.details.MoveDetailsPage
import io.github.lexadiky.pdx.feature.news.NewsFeatureToggle
import io.github.lexadiky.pdx.feature.news.feed.NewsFeedPage
import io.github.lexadiky.pdx.feature.pokemon.details.PokemonDetailsPage
import io.github.lexadiky.pdx.feature.pokemon.list.PokemonListPage
import io.github.lexadiky.pdx.feature.settings.SettingsPage
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsPage
import io.github.lexadiky.pdx.feature.spritegallery.SpriteGalleryPage
import io.github.lexadiky.pdx.feature.type.details.TypeDetailsPage
import io.github.lexadiky.pdx.feature.typechart.TypePage
import io.github.lexadiky.pdx.feature.whois.WhoIsPage
import io.github.lexadiky.pdx.library.featuretoggle.FeatureToggleManager
import io.github.lexadiky.pdx.library.featuretoggle.ifEnabled

@Composable
fun routing(): NaviNavGraphBuilder.() -> Unit {
    val toggleManager = di.inject<FeatureToggleManager>()

    return remember(toggleManager) {
        {
            page("pdx://home") { HomePage() }
            page("pdx://settings") { SettingsPage() }
            page("pdx://settings/achievements") { AchievementSettingsPage() }
            toggleManager.ifEnabled(NewsFeatureToggle) {
                page("pdx://news") { NewsFeedPage() }
            }
            page("pdx://pokemon?types={types}") { PokemonListPage() }
            page("pdx://pokemon/{id}") {
                PokemonDetailsPage(pokemonId =  argument(name = "id"))
            }
            page("pdx://type") { TypePage() }
            modal("pdx://type/{id}") {
                val id =  { error("id required") }
                TypeDetailsPage(typeId = argument(name = "id"))
            }
            page("pdx://game/whois") { WhoIsPage() }
            fullScreen("pdx://pokemon/{speciesId}/{varietyId}/sprites") {
                SpriteGalleryPage(argument(name = "varietyId"))
            }
            modal("pdx://ability/{id}") {
                AbilityDetailsPage(id = argument("id"))
            }
            modal("pdx://move/{id}?is_modal=true") {
                MoveDetailsPage(moveId = this.argument("id"))
            }

            DebugPanelFeature.routing(this, toggleManager)
        }
    }
}
