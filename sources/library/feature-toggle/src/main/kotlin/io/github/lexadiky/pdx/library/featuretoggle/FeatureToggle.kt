package io.github.lexadiky.pdx.library.featuretoggle

sealed interface FeatureToggle<Value : Any> {

    val id: String

    val group: String

    val defaultValue: Value
}
