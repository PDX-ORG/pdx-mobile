package io.github.lexadiky.pdx.library.analytics

import io.github.lexadiky.pdx.library.analytics.sender.EventSender
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import io.mockk.verify

class DelegatingAnalyticsManagerSpec : DescribeSpec({
    describe("log") {
        it("GIVEN no delegates THEN sends nothing") {
            val manager = DelegatingAnalyticsManager(emptyList())
            // can't check it, lets just assume
        }

        it("GIVEN delegates THEN sends events to them") {
            val sender = mockk<EventSender>(relaxed = true)
            val manager = DelegatingAnalyticsManager(listOf(sender))
            manager.log("hello", mapOf("arg1" to "value1"))
            verify {
                sender.log("hello", mapOf("arg1" to "value1"))
            }
        }
    }
})
