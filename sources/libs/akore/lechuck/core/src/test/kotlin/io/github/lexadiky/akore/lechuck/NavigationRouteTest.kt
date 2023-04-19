package io.github.lexadiky.akore.lechuck

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NavigationRouteTest {

    @Nested
    inner class isHttp() {

        @CsvSource(
            "http://sample.com, true",
            "https://sample.com, true",
            "httpx://sample.com, false",
            "lechuck://sample.com, false"
        )
        @ParameterizedTest(name = "GIVEN {0} THEN isHttp returns {1}")
        fun `WHEN uri THEN isHttp=isTrue`(uri: String, isTrue: Boolean) {
            assertEquals(isTrue, NavigationRoute.from(uri).isHttp) {
                if (isTrue) {
                    "$uri should be detected as url"
                } else {
                    "$uri should not be detected as url"
                }
            }
        }
    }

    @Nested
    internal class scheme() {

        @Test
        fun `GIVEN uri without scheme THEN return null`() {
            assertNull(NavigationRoute.from("a.com").scheme)
        }
    }
}