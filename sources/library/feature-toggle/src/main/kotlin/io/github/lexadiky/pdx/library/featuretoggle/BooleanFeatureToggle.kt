package io.github.lexadiky.pdx.library.featuretoggle

class BooleanFeatureToggle(
    override val id: String,
    override val group: String,
    override val defaultValue: Boolean
) : FeatureToggle<Boolean>
