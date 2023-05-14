package io.github.lexadiky.pdx.feature.rateapp

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.lib.arc.ViewModelSocket
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import io.github.lexadiky.pdx.lib.system.ReviewRequester
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class RateAppSocket(
    private val reviewManager: ReviewRequester,
    microdataManager: MicrodataManager
) : ViewModelSocket<RateAppState, RateAppAction>(RateAppState()) {

    private val microdata = microdataManager.acquire(this, MICRO_DATABASE_NAME)

    init {
        viewModelScope.launch {
            val isReviewRequested = microdata.boolean(KEY_IS_REVIEW_REQUESTED)
            val res = isReviewRequested.get()
            if (res != true) {
                isReviewRequested.set(true)
                delay(REVIEW_DELAY_MINUTES.minutes)
                state = state.copy(isDialogOpen = true)
            }
        }
    }

    override suspend fun onAction(action: RateAppAction) {
        state = when (action) {
            RateAppAction.DislikePressed -> state.copy(isDialogOpen = false)
            RateAppAction.LikePressed -> {
                requestReview()
                state.copy(isDialogOpen = false)
            }
            RateAppAction.DialogDismiss -> state.copy(isDialogOpen = false)
        }
    }

    private suspend fun requestReview() {
        withContext(SupervisorJob() + Dispatchers.Default) {
            launch {
                reviewManager.request()
            }
        }
    }

    companion object {

        private const val MICRO_DATABASE_NAME = "review_dialog"
        private const val KEY_IS_REVIEW_REQUESTED = "is_review_requested133"
        private const val REVIEW_DELAY_MINUTES = 5
    }
}
