package com.group16.fitapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

class Pedometer : Fragment(R.layout.frag_pedometer),SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.frag_pedometer, container, false)
        super.onCreate(savedInstanceState)
        loadData()
        resetSteps()
        return view
    }
    override fun onResume(){
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor == null){
            Toast.makeText(activity, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var tv_stepsTaken = view?.findViewById<TextView>(R.id.tv_stepsTaken)
        if( running) {
            totalSteps = event!!.values[0]

            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            tv_stepsTaken?.text = ("$currentSteps")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
    private fun resetSteps() {
        var tv_stepsTaken = view?.findViewById<TextView>(R.id.tv_stepsTaken)
        tv_stepsTaken?.setOnClickListener{
            Toast.makeText(activity, "Long Tap to reset steps", Toast.LENGTH_SHORT).show()
        }
        tv_stepsTaken?.setOnLongClickListener{
            previousTotalSteps = totalSteps
            tv_stepsTaken.text = 0.toString()
            saveData()
            true
        }
    }
    private fun saveData() {
        val sharedPreferences = this.activity?.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val editor = sharedPreferences?.edit()
        editor?.putFloat("key1", previousTotalSteps)
        editor?.apply()
    }
    private fun loadData() {
        val sharedPreferences = this.activity?.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences?.getFloat("key1", 0f)

        Log.d("Pedometer", "$savedNumber")

        if (savedNumber != null) {
            previousTotalSteps = savedNumber
        }
    }
}