package io.github.lexadiky.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    // TODO disabled, because CI can't handle it @Test
    fun generate() {
        rule.collectBaselineProfile("io.github.lexadiky.pdx") {
            pressHome()
            startActivityAndWait()
        }
    }
}
