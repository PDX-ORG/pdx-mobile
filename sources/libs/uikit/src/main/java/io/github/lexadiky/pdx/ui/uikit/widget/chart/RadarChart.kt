package io.github.lexadiky.pdx.ui.uikit.widget.chart

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.charts.RadarChart as RadarChartView

data class RadarChartDataSet(val entries: List<Entry>) {

    data class Entry(val label: String, val value: Float)
}

@Composable
fun RadarChart(dataSets: List<RadarChartDataSet>, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RadarChartView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                background = ColorDrawable(Color.DKGRAY)
            }
        },
        update = { chart ->
            val radarData = RadarData()
            for (dataSet in dataSets) {
                val ds = RadarDataSet(dataSet.entries.map { RadarEntry(it.value) }, null)
                ds.color = android.graphics.Color.TRANSPARENT
                ds.fillColor = Color.BLUE
                ds.setDrawFilled(true)
                ds.valueTextSize = 10f
                ds.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return value.toInt().toString()
                    }
                }
                radarData.addDataSet(ds)
            }
            chart.data = radarData
            chart.isRotationEnabled = false

            chart.yAxis.axisMinimum = 0f
            chart.yAxis.axisMaximum = 225f
            chart.yAxis.setDrawLabels(false)
            chart.yAxis.setCenterAxisLabels(false)

            chart.xAxis.setCenterAxisLabels(false)
            chart.xAxis.textSize = 12f
            chart.xAxis.labelCount = 6
            chart.xAxis.setDrawLabels(true)
            chart.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    val entry = dataSets.first().entries.getOrNull(value.toInt())
                    return entry?.label ?: ""
                }
            }
            chart.setExtraOffsets(0f, 0f, 0f, 0f)

            chart.description = Description().apply {
                text = ""
            }
        }
    )
}
