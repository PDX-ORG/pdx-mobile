package io.github.lexadiky.pdx.library.core.utils

import arrow.core.Either
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.core.utils.asEither
import io.github.lexadiky.pdx.library.core.utils.asLce
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual

class EitherConverterSpec : DescribeSpec({
    describe("Result.asEither") {
        it("given failed should be left") {
            val exception = Exception()
            Result.failure<String>(exception).asEither() shouldBeEqual Either.Left(exception)
        }

        it("given successful should be right") {
            Result.success("hello").asEither() shouldBeEqual Either.Right("hello")
        }
    }

    describe("Either.asLce") {
        it("given Left should be Error") {
            val exception = Exception()
            Either.Left(exception).asLce() shouldBeEqual Lce.Error(exception)
        }

        it("given Right should be Content") {
            Either.Right("hello").asLce() shouldBeEqual Lce.Content("hello")
        }
    }
})
