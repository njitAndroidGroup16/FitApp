package com.group16.fitapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class HomeFragment:Fragment(R.layout.frag_home) {
    private lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_home, container, false)
        barChart = view.findViewById(R.id.activityChart)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBarChart()
        loadBarChartData()
    }

    private fun setupBarChart() {
        // Customize bar chart appearance
        barChart.description.isEnabled = false
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(true)

        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false
    }

    private fun loadBarChartData() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 30f))
        entries.add(BarEntry(2f, 50f))
        entries.add(BarEntry(3f, 70f))
        entries.add(BarEntry(4f, 90f))

        val dataSet = BarDataSet(entries, "Bar Chart")
        dataSet.color = Color.BLUE

        val data = BarData(dataSet)
        barChart.data = data
        barChart.invalidate()
    }

}