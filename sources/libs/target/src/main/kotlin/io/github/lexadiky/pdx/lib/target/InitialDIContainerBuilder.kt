package io.github.lexadiky.pdx.lib.target

import android.content.Context
import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.alice.builder
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.lib.FeatureToggleModule
import io.github.lexadiky.pdx.lib.firebase.FirebaseModule
import io.github.lexadiky.pdx.lib.fs.RoboFsModule
import network.NetworkModule
import io.github.lexadiky.pdx.lib.target.util.DIContainerWatchdog
import io.github.lexadiky.pdx.ui.uikit.UikitModule

class InitialDIContainerBuilder {

    fun build(context: Context): DIContainer {
        return DIContainer.builder()
            .modules(
                ApplicationModule(context),
                FirebaseModule(context),
                PokemonDomainModule,
                AnalyticsModule,
                NetworkModule,
                FeatureToggleModule,
                UikitModule,
                RoboFsModule,
                MicrodataModule
            )
            .inspector(DIContainerWatchdog.create(context))
            .build()
    }
}
