package io.github.lexadiky.pdx.lib.system

import android.app.Activity
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single
import java.lang.ref.WeakReference

fun SystemModule(activity: Activity) = eagerModule("lib-system") {
    single<ReviewRequester> { ReviewRequesterImpl(WeakReference(activity)) }
}
