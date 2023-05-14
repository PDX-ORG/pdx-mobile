package io.github.lexadiky.pdx.lib.system

import android.app.Activity
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewManagerFactory
import java.lang.ref.WeakReference
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ReviewRequester {

    suspend fun request()
}

internal class ReviewRequesterImpl(
    private val activityRef: WeakReference<Activity>
): ReviewRequester {

    private val mutex = Mutex()

    override suspend fun request() = mutex.withLock {
        val activity = activityRef.get() ?: return
        val manager = ReviewManagerFactory.create(activity)
        val reviewRequest = manager.requestReview()
        manager.launchReview(activity, reviewRequest)
    }
}
