package io.github.lexadiky.pdx.lib.target

import android.app.Application
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.FeatureToggleModule
import io.github.lexadiky.pdx.lib.analytics.AnalyticsModule
import io.github.lexadiky.pdx.lib.arc.di.DIContainer
import io.github.lexadiky.pdx.lib.network.NetworkModule
import io.github.lexadiky.pdx.lib.target.util.ApplicationInitializer
import io.github.lexadiky.pdx.lib.target.util.LogcatLoggerDelegate
import io.github.lexadiky.pdx.ui.uikit.UikitModule

abstract class BaseTargetApplication : Application() {

    val diContainer = DIContainer(
        AnalyticsModule,
        NetworkModule,
        FeatureToggleModule,
        UikitModule,
        ApplicationModule(this)
    )

    override fun onCreate() {
        super.onCreate()
        BLogger.install(LogcatLoggerDelegate())
        ApplicationInitializer()
            .task("sync_feature_toggles") { diContainer.lookup<FeatureToggleManager>().sync() }
            .run()
    }
}
