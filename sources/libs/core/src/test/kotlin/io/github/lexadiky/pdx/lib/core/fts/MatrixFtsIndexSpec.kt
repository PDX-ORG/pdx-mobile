package io.github.lexadiky.pdx.lib.core.fts

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class MatrixFtsIndexSpec : DescribeSpec({
    describe("matching word clause") {
        val index = MatrixFtsIndex()
        index.addClause("hello")

        it("should match middle part of the word") {
            index.matches("ello").shouldBeTrue()
        }

        it("should match even single letter") {
            index.matches("e").shouldBeTrue()
        }

        it("should not match regexp") {
            index.matches(".*").shouldBeFalse()
        }

        it("should not match empty string") {
            index.matches("").shouldBeFalse()
        }

        it("should not match blank string") {
            index.matches(" ").shouldBeFalse()
        }

        it("should match full clause") {
            index.matches("hello").shouldBeTrue()
        }

        it("should not match if clause is substring of query") {
            index.matches("ohhellomark").shouldBeFalse()
        }
    }

    describe("matching sentence clause") {
        val index = MatrixFtsIndex()
        index.addClause("hello world")

        it("should not match blank string") {
            index.matches(" ").shouldBeFalse()
        }

        it("should match query with space characters") {
            index.matches("o w").shouldBeTrue()
        }
    }

    describe("adding clauses") {
        it("matching result is not cached") {
            val index = MatrixFtsIndex()
            index.addClause("hello")
            index.matches("goodbye").shouldBeFalse()
            index.addClause("goodbye")
            index.matches("goodbye").shouldBeTrue()
        }

        it("should support adding clauses over base size") {
            val index = MatrixFtsIndex(3)
            repeat(5) { idx ->
                index.addClause("clause-$idx")
            }

            index.matches("clause-0").shouldBeTrue()
            index.matches("clause-4").shouldBeTrue()
        }
    }
})
