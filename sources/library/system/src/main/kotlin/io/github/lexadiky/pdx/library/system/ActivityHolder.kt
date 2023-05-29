package io.github.lexadiky.pdx.library.system

import android.app.Activity
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.info
import java.lang.ref.WeakReference
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class ActivityHolder {
    @PublishedApi
    internal var activityRef: WeakReference<Activity> = WeakReference(null)
    @PublishedApi
    internal val rwLock = ReentrantReadWriteLock()

    fun take(activity: Activity) {
        rwLock.write {
            activityRef = WeakReference(activity)
        }
    }

    inline fun <T : Any> use(owner: Any, acceptor: (Activity) -> T): T? {
        val activity = rwLock.read { activityRef.get() }
        if (activity == null) {
            BLogger.tag("ActivityHolder")
                .error("tried to acquire activity, but it was unavailable", IllegalStateException())
            return null
        }

        BLogger.tag("ActivityHolder")
            .info("$owner: acquired activity ${activity.componentName.flattenToString()}")

        return acceptor(activity).also {
            BLogger.tag("ActivityHolder")
                .info("$owner: processed activity, should let it go")
        }
    }
}
