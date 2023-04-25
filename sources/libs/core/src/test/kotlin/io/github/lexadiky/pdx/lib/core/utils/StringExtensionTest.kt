package io.github.lexadiky.pdx.lib.core.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

class StringExtensionTest {

    @Nested
    inner class removeNewLines {

        @TestFactory
        fun variants(): List<DynamicTest> =
            listOf("abc" to "abc", "" to "", "a\nb" to "ab", "\n" to "").map { (input, expected) ->
                DynamicTest.dynamicTest("GIVEN '$input' THEN '$expected'") {
                    Assertions.assertEquals(expected, input.removeNewLines())
                }
            }
    }
}