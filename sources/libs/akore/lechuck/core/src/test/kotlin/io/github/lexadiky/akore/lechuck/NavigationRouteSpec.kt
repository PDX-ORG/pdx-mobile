package io.github.lexadiky.akore.lechuck

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class NavigationRouteSpec : DescribeSpec({
    describe("isHttp") {
        forAll(
            table(
                headers("url", "is http"),
                row("http://sample.com", true),
                row("https://sample.com", true),
                row("httpx://sample.com", false),
                row("lechuck://sample.com", false)
            )
        ) { url: String, expectedIsHttp: Boolean ->
            it("GIVEN $url THEN isHttp returns $expectedIsHttp}") {
                val actualIsHttp = NavigationRoute.from(url).isHttp
                actualIsHttp shouldBe expectedIsHttp
            }
        }
    }

    describe("from(String)") {
        it("GIVEN a.com THEN scheme is null") {
            val scheme = NavigationRoute.from("a.com").scheme
            scheme shouldBe null
        }
    }
})
