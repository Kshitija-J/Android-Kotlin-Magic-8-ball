package com.example.magic_eight

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.sqrt

private const val TAG = "ShakeDetector"



class ShakeDetector(private val onShake: () -> Unit) : SensorEventListener {

    private val shakeThresholdGravity = 1.2f
    private val shakeSlopTimeMs = 100
    private var shakeTimestamp: Long = 0

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Can be left empty
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val gX = event.values[0] / SensorManager.GRAVITY_EARTH
            val gY = event.values[1] / SensorManager.GRAVITY_EARTH
            val gZ = event.values[2] / SensorManager.GRAVITY_EARTH

            // gForce will be close to 1 when there is no movement.
            val gForce = sqrt(gX * gX + gY * gY + gZ * gZ)

            if (gForce > shakeThresholdGravity) {
                val now = System.currentTimeMillis()
                // Ignore shake events too close to each other (500ms)
                if (shakeTimestamp + shakeSlopTimeMs > now) {
                    return
                }

                shakeTimestamp = now
                Log.d(TAG, "Shake detected")
                onShake()
            }
        }
    }
}