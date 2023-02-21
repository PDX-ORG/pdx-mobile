package io.github.lexadiky.pdx.feature.debugpanel

import android.app.Application
import android.content.pm.ApplicationInfo
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.navigation.NaviNavGraphBuilder

object DebugPanelFeature {

    fun routing(builder: NaviNavGraphBuilder, featureToggleManager: FeatureToggleManager) = builder.apply {
        if (featureToggleManager.isDebug) {
            page("pdx://debug/panel") { DebugPanelPage() }
            page("pdx://debug/panel/navigation-schema") { NavigationGraphPanelPage() }
        }
    }
}