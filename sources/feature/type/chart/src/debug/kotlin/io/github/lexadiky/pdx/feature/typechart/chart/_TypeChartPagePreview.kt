package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.compose.runtime.Composable
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.feature.typechart.entity.DamageRelationsSubState
import io.github.lexadiky.pdx.library.system.DefaultPreview
import io.github.lexadiky.pdx.library.system.PagePreview

@[Composable DefaultPreview]
internal fun _TypeChartPagePreview() = PagePreview {
    TypeChartPage(
        object : TypeChartSocket(
            TypeChartState(
                damageRelationsSubState = DamageRelationsSubState(
                    selectedTypes = listOf(PokemonType.DRAGON, PokemonType.FIRE),
                    damageRelations = mapOf(
                        PokemonType.DRAGON to PokemonTypeDamageRelation(
                            doubleDamageTo = listOf(
                                PokemonType.DRAGON,
                                PokemonType.FAIRY
                            ),
                            doubleDamageFrom = listOf(
                                PokemonType.BUG
                            ),
                            halfDamageFrom = listOf(
                                PokemonType.FLYING,
                                PokemonType.DARK,
                                PokemonType.GRASS
                            ),
                            halfDamageTo = emptyList(),
                            zeroDamageFrom = listOf(
                                PokemonType.NORMAL
                            ),
                            zeroDamageTo = listOf(
                                PokemonType.NORMAL
                            )
                        )
                    )
                )
            )
        ) {
            override suspend fun onAction(action: TypeChartAction) = Unit
        }
    )
}
