package io.github.lexadiky.pdx.feature.rateapp

import androidx.lifecycle.viewModelScope
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewManager
import io.github.lexadiky.pdx.lib.arc.ViewModelSocket
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class RateAppSocket(
    private val reviewManager: ReviewManager,
    microdataManager: MicrodataManager
) : ViewModelSocket<RateAppState, RateAppAction>(RateAppState()) {

    private val microdata = microdataManager.acquire(this, MICRO_DATABASE_NAME)

    init {
        viewModelScope.launch {
            val isReviewRequested = microdata.boolean(KEY_IS_REVIEW_REQUESTED)
            isReviewRequested.observe()
                .onEach { delay(REVIEW_DELAY_MINUTES.seconds) }
                .collectLatest { res ->
                    if (res == false) {
                        isReviewRequested.set(true)
                        state = state.copy(isDialogOpen = true)
                    }
                }
        }
    }

    override suspend fun onAction(action: RateAppAction) {
        state = when (action) {
            RateAppAction.DislikePressed -> state.copy(isDialogOpen = false)
            RateAppAction.LikePressed -> {
                reviewManager.requestReview()
                state.copy(isDialogOpen = false)
            }
            RateAppAction.DialogDismiss -> state.copy(isDialogOpen = false)
        }
    }

    companion object {

        private const val MICRO_DATABASE_NAME = "review_dialog"
        private const val KEY_IS_REVIEW_REQUESTED = "is_review_requested1"
        private const val REVIEW_DELAY_MINUTES = 5
    }
}
