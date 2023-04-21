package io.github.lexadiky.pdx.feature.debugpanel

import io.github.lexadiky.akore.lechuck.robo.NaviNavGraphBuilder
import io.github.lexadiky.pdx.lib.FeatureToggleManager

object DebugPanelFeature {

    fun routing(builder: NaviNavGraphBuilder, featureToggleManager: FeatureToggleManager) =
        builder.apply {
            if (featureToggleManager.isDebug) {
                page("pdx://debug/panel") { DebugPanelPage() }
                page("pdx://debug/panel/navigation-schema") { NavigationGraphPanelPage() }
            }
        }
}
