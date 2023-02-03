package io.github.lexadiky.pdx.feature.widget.launcher

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text

class GlanceLauncherWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(text = "HELLO WORLD")
    }
}