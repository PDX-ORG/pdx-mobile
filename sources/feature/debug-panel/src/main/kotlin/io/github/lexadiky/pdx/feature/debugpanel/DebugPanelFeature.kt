package io.github.lexadiky.pdx.feature.debugpanel

import io.github.lexadiky.pdx.feature.debugpanel.subpage.NavigationGraphPanelPage
import io.github.lexadiky.pdx.library.featuretoggle.FeatureToggleManager
import io.github.lexadiky.pdx.library.nibbler.android.graph.RoutingBuilderContext

object DebugPanelFeature {

    fun routing(builder: RoutingBuilderContext, featureToggleManager: FeatureToggleManager) =
        builder.apply {
            if (featureToggleManager.isDebug) {
                page("pdx://debug/panel") { DebugPanelPage() }
                page("pdx://debug/panel/navigation-schema") { NavigationGraphPanelPage() }
            }
        }
}
