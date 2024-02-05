package com.example.magic_eight

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.magic_eight.databinding.ActivityMainBinding

private const val TAG =  "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var magicView: MagicView
    private lateinit var sensorManager: SensorManager
    private var shakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        magicView = ViewModelProvider(this)[MagicView::class.java]

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector {
            // Always generate a new random response on shake
            magicView.generateNewRandomResponse()
            updateUI()
        }
        updateUI()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(shakeDetector,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI)
        Log.d(TAG, "ShakeDetector registered")

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(shakeDetector)
        Log.d(TAG, "ShakeDetector unregistered")
    }

    private fun updateUI() {
        // Display the current response
        magicView.currentResponse?.let {
            binding.spiritting.setText(it.textResId)
        }
    }
}