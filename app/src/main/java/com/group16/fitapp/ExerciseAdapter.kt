package com.group16.fitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group16.fitapp.R
class ExerciseAdapter (private val exerciseList: List<Exercise>):

    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_exercise, parent, false)
        return ExerciseViewHolder(view)
    }
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseNameView: TextView = itemView.findViewById(R.id.exerciseName)
        val targetMuscleView: TextView = itemView.findViewById(R.id.targetMuscle)

    }


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = exerciseList[position]
        holder.exerciseNameView.text = item.name
        holder.targetMuscleView.text = item.target
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

