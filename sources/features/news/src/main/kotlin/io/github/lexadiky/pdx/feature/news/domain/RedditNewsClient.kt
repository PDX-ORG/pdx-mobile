package io.github.lexadiky.pdx.feature.news.domain

import arrow.core.Either
import io.github.lexadiky.pdx.feature.news.entity.domain.RedditResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RedditNewsClient(private val httpClient: HttpClient) {

    suspend fun load(): Either<Throwable, RedditResponse> = Either.catch {
        httpClient.get(SUBREDDIT_URL)
            .body()
    }

    companion object {

        private const val SUBREDDIT_URL = "https://www.reddit.com/r/PokeLeaks/.json"
    }
}
