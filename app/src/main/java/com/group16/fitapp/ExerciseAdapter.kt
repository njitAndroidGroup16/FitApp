package com.group16.fitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group16.fitapp.R
class ExerciseAdapter (private val exerciseList: List<Exercise>):

    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        // Add more views if needed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = exerciseList[position]
        holder.textView.text = item.name
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

