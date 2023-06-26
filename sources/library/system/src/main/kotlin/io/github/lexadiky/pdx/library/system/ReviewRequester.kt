package io.github.lexadiky.pdx.library.system

import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewManagerFactory
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ReviewRequester {

    suspend fun request()
}

internal class ReviewRequesterImpl(
    private val activityHolder: ActivityHolder
): ReviewRequester {

    private val mutex = Mutex()

    override suspend fun request(): Unit = mutex.withLock {
        BLogger.tag("ReviewRequester")
            .info("review requested, mutex locked")
        activityHolder.use(this) { activity ->
            val manager = ReviewManagerFactory.create(activity)
            val reviewRequest = manager.requestReview()
            manager.launchReview(activity, reviewRequest)
        }
        BLogger.tag("ReviewRequester")
            .info("review launched, mutex unlocked")
    }
}
