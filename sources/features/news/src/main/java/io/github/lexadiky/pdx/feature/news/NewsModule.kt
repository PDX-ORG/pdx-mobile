package io.github.lexadiky.pdx.feature.news

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.news.domain.GetNewsFeedUseCase
import io.github.lexadiky.pdx.feature.news.domain.RedditNewsClient
import io.github.lexadiky.pdx.feature.news.feed.NewsFeedViewModel
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

internal val NewsModule by module("news") {
    internal {
        single { RedditNewsClient(inject()) }
        single { GetNewsFeedUseCase(inject()) }
        singleViewModel { NewsFeedViewModel(inject(), inject()) }
    }
}
