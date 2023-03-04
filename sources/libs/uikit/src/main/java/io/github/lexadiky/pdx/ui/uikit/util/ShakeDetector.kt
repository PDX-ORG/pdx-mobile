package io.github.lexadiky.pdx.ui.uikit.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlin.math.sqrt
import kotlin.time.Duration.Companion.seconds

class ShakeDetector(context: Context) {

    private val sensorManager = context.getSystemService<SensorManager>()!!
    private val eventBus: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0, extraBufferCapacity = 1)
    val events: Flow<Unit> get() = eventBus.debounce(EVENT_DEBOUNCE_TIME.seconds)

    init {
        sensorManager.registerListener(
            Listener(),
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private inner class Listener : SensorEventListener {

        private var acceleration = DEFAULT_ACCELERATION
        private var accelerationCurrent = SensorManager.GRAVITY_EARTH
        private var accelerationLast = SensorManager.GRAVITY_EARTH

        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            accelerationLast = accelerationCurrent
            accelerationCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = accelerationCurrent - accelerationLast
            acceleration = acceleration * ACCELERATION_DOWN_MOD + delta
            if (acceleration > ACCELERATION_THRESHOLD) {
                eventBus.tryEmit(Unit)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
    }

    companion object {

        private const val DEFAULT_ACCELERATION = 10f
        private const val ACCELERATION_THRESHOLD = 15
        private const val ACCELERATION_DOWN_MOD = 0.9f
        private const val EVENT_DEBOUNCE_TIME = 1
    }
}
