package io.github.lexadiky.pdx.library.target

import android.app.Application
import io.github.lexadiky.akore.alice.lookup
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.logcat.logcat
import io.github.lexadiky.pdx.library.target.init.ApplicationInitializer
import io.github.lexadiky.pdx.library.target.init.impl.PrefetchPokemonDataAsyncTask
import io.github.lexadiky.pdx.library.target.util.crashlytics

abstract class BaseTargetApplication : Application() {

    val diContainer by lazy {
        InitialDIContainerBuilder()
            .build(this)
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
        registerActivityLifecycleCallbacks(BaseTargetLifecycleCallbacks(this))
    }
}
