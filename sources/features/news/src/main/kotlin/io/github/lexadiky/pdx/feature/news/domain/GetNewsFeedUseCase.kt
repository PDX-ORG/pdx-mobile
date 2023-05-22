package io.github.lexadiky.pdx.feature.news.domain

import arrow.core.Either
import arrow.core.computations.ResultEffect.bind
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.feature.news.entity.domain.RedditResponse
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import java.net.URI
import kotlinx.datetime.Instant

internal class GetNewsFeedUseCase(private val redditNewsClient: RedditNewsClient) {

    suspend operator fun invoke(): Either<GenericError, List<NewsFeedItem>> = either {
        val response: RedditResponse = redditNewsClient.load()
            .mapLeft { GenericError("can't load news feed", it) }
            .bind()

        val children = response.data.children

        children.map { child ->
            NewsFeedItem(
                uri = URI.create(child.data.url),
                authorUri = URI.create(USER_LINK + child.data.author),
                title = StringResource.from(child.data.title),
                author = child.data.author,
                preview = child.data.preview?.images?.firstOrNull()?.resolutions?.lastOrNull()?.url?.let {
                    ImageResource.from(it)
                },
                time = Instant.fromEpochSeconds(child.data.createdTimestamp.toLong())
                    .let { StringResource.from(it) }
            )
        }
    }

    companion object {

        private const val USER_LINK = "https://www.reddit.com/user/"
    }
}
