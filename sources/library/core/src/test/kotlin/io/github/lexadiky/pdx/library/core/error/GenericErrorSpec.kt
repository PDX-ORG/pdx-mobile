package io.github.lexadiky.pdx.library.core.error

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull

class GenericErrorSpec : DescribeSpec({
    describe("originate") {
        GenericError.originate("something wrong").cause.shouldBeNull()
    }

    describe("default constructor") {
        val expectedCause = Exception("world")
        GenericError("hello", expectedCause).apply {
            message!! shouldBeEqual "hello"
            cause!! shouldBeEqual expectedCause
        }
    }
})
