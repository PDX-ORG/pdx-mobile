package io.github.lexadiky.pdx.lib.target

import android.app.Application
import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.logcat.logcat
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.FeatureToggleModule
import io.github.lexadiky.pdx.lib.analytics.AnalyticsModule
import io.github.lexadiky.pdx.lib.fs.RoboFsModule
import io.github.lexadiky.pdx.lib.network.NetworkModule
import io.github.lexadiky.pdx.lib.target.util.ApplicationInitializer
import io.github.lexadiky.pdx.lib.target.util.crashlytics
import io.github.lexadiky.pdx.ui.uikit.UikitModule

abstract class BaseTargetApplication : Application() {

    val diContainer by lazy {
        DIContainer(
            AnalyticsModule,
            NetworkModule,
            FeatureToggleModule,
            UikitModule,
            ApplicationModule(this),
            RoboFsModule
        )
    }

    override fun onCreate() {
        super.onCreate()
        BLogger.configure {
            source pipeTo logcat
            source pipeTo crashlytics
        }
        ApplicationInitializer()
            .task("sync_feature_toggles") { diContainer.lookup<FeatureToggleManager>().sync() }
            .run()
    }
}
