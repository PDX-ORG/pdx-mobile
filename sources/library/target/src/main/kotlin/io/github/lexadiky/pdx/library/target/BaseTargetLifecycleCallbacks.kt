package io.github.lexadiky.pdx.library.target

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.github.lexadiky.akore.alice.lookup
import io.github.lexadiky.pdx.library.system.ActivityHolder

class BaseTargetLifecycleCallbacks(private val application: BaseTargetApplication) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        application.diContainer.lookup<ActivityHolder>()
            .take(activity)
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
