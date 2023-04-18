package io.github.lexadiky.pdx.lib.target

import android.app.Application
import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.alice.builder
import io.github.lexadiky.akore.alice.lookup
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.logcat.logcat
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.lib.FeatureToggleModule
import io.github.lexadiky.pdx.lib.firebase.FirebaseModule
import io.github.lexadiky.pdx.lib.fs.RoboFsModule
import io.github.lexadiky.pdx.lib.network.NetworkModule
import io.github.lexadiky.pdx.lib.target.init.ApplicationInitializer
import io.github.lexadiky.pdx.lib.target.init.impl.PrefetchPokemonDataAsyncTask
import io.github.lexadiky.pdx.lib.target.util.DIContainerWatchdog
import io.github.lexadiky.pdx.lib.target.util.crashlytics
import io.github.lexadiky.pdx.ui.uikit.UikitModule

abstract class BaseTargetApplication : Application() {

    val diContainer by lazy {
        DIContainer.builder()
            .modules(
                ApplicationModule(this),
                FirebaseModule(this),
                PokemonDomainModule,
                AnalyticsModule,
                NetworkModule,
                FeatureToggleModule,
                UikitModule,
                RoboFsModule,
            )
            .inspector(DIContainerWatchdog.create(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        BLogger.configure {
            source pipeTo logcat
            source pipeTo crashlytics
        }
        ApplicationInitializer(diContainer)
            .asyncTask(PrefetchPokemonDataAsyncTask(diContainer.lookup()))
            .run()
    }
}
