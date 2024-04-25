package com.group16.fitapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class HomeFragment:Fragment(R.layout.frag_home) {
    private lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_home, container, false)
        barChart = view.findViewById(R.id.activityChart)
        val userName = "John"
        val welcomeMessage = view.findViewById<TextView>(R.id.welcome)
        val currentMessage = welcomeMessage.text.toString()
        welcomeMessage.text = currentMessage + userName
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBarChart()
    }

    data class BarChartData(val date: String, val value: Float)

    private val barEntries = listOf(
        BarChartData("3 days ago", 20f),  // 3 days ago
        BarChartData("2 days ago", 35f),  // 2 days ago
        BarChartData("Yesterday", 15f),  // yesterday
        BarChartData("Today", 40f)   // today
    )

    private fun setupBarChart() {
        val barDataSet = BarDataSet(barEntries.mapIndexed { index, barChartData ->
            BarEntry(index.toFloat(), barChartData.value)
        }, null)
        val data = BarData(barDataSet)
        barChart.data = data
        val leftAxis = barChart.axisLeft
        leftAxis.valueFormatter = YourValueFormatter("min")
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(false)
        barChart.description.isEnabled = false


        barChart.invalidate()
    }
    class YourValueFormatter(private val unit: String) : com.github.mikephil.charting.formatter.ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return "$value $unit" // Append the unit to the formatted value
        }
    }

}