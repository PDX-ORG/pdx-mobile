package io.github.lexadiky.pdx.feature.news.feed

import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem

internal sealed interface NewsFeedAction {

    object DismissError : NewsFeedAction

    sealed interface Navigate : NewsFeedAction {

        class Article(val item: NewsFeedItem) : Navigate

        class Author(val item: NewsFeedItem) : Navigate
    }
}
