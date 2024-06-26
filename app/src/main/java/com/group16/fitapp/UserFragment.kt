package com.group16.fitapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class UserFragment:Fragment(R.layout.frag_user) {
    private lateinit var lineChart: LineChart
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_user, container, false)
        lineChart = view.findViewById(R.id.lineChart)
        pieChart = view.findViewById(R.id.pieChart)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLineChart()
        setData() // Set your data points here
        populatePieChart()
    }

    private fun setupLineChart() {
        // Customize the line chart as needed
        val description = Description()
        description.text = "Your Chart Description"
        lineChart.description = description
        lineChart.setDrawGridBackground(false)
        lineChart.legend.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
    }

    private fun setData() {
        // Add data points to the line chart
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 20f))
        entries.add(Entry(2f, 15f))
        entries.add(Entry(3f, 25f))
        entries.add(Entry(4f, 18f))
        entries.add(Entry(5f, 30f))

        val dataSet = LineDataSet(entries, "Label") // Customize label as needed
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setCircleColor(Color.BLUE)
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        lineChart.description.isEnabled=false
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    private fun populatePieChart() {
        val entries = listOf(
            PieEntry(30f, "Abs"),
            PieEntry(20f, "Quads"),
            PieEntry(50f, "Lats")
        )

        val dataSet = PieDataSet(entries, "Categories")
        dataSet.colors = listOf(Color.RED, Color.GREEN, Color.BLUE)
        pieChart.description.isEnabled=false
        val data = PieData(dataSet)
        pieChart.data = data

        // Refresh the chart
        pieChart.invalidate()
    }
}