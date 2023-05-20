package io.github.lexadiky.pdx.feature.news.feed

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.feature.news.domain.GetNewsFeedUseCase
import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.lib.arc.ViewModelSocket
import io.github.lexadiky.pdx.lib.errorhandler.classify
import kotlinx.coroutines.launch

internal class NewsFeedSocket(
    private val getNewsFeedUseCase: GetNewsFeedUseCase,
    private val navigator: Navigator,
) : ViewModelSocket<NewsFeedState, NewsFeedAction>(NewsFeedState()) {

    init {
        viewModelScope.launch {
            when (val eitherFeed = getNewsFeedUseCase().classify(NewsFeedSocket::class)) {
                is Either.Left -> state = state.copy(error = eitherFeed.value)
                is Either.Right -> state = state.copy(items = eitherFeed.value)
            }
        }
    }

    override suspend fun onAction(action: NewsFeedAction) {
        when (action) {
            NewsFeedAction.DismissError -> dismissError()
            is NewsFeedAction.Navigate.Article -> openNewsItem(action.item)
            is NewsFeedAction.Navigate.Author -> openNewsItemAuthor(action.item)
        }
    }

    private fun dismissError() {
        state = state.copy(error = null)
    }

    private fun openNewsItem(item: NewsFeedItem) {
        viewModelScope.launch {
            navigator.navigate(item.uri)
        }
    }

    private fun openNewsItemAuthor(item: NewsFeedItem) {
        viewModelScope.launch {
            navigator.navigate(item.authorUri)
        }
    }
}
