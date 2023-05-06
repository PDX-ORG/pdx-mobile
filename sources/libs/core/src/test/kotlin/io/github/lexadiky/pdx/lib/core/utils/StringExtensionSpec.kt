package io.github.lexadiky.pdx.lib.core.utils

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class StringExtensionSpec : DescribeSpec({
    describe("removeNewLines") {
        forAll(
            table(
                headers("input", "expected output"),
                row("abc", "abc"),
                row("", ""),
                row("a\nb", "a b"),
                row("\n", " ")
            )
        ) { input: String, expectedOutput: String ->
            it("GIVEN '$input' THEN '$expectedOutput'") {
                input.removeNewLines() shouldBe expectedOutput
            }
        }
    }
})
