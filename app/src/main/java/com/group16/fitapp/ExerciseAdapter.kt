package com.group16.fitapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.group16.fitapp.R
class ExerciseAdapter (
    private val exerciseList: List<Exercise>,
    private val mListener: OnListFragmentInteractionListener?
):

    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_exercise, parent, false)
        return ExerciseViewHolder(view)
    }
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mExercise: Exercise? = null
        val exerciseNameView: TextView = itemView.findViewById(R.id.exerciseName)
        val targetMuscleView: TextView = itemView.findViewById(R.id.targetMuscle)
        val exerciseGif: ImageView = itemView.findViewById(R.id.exerciseGif)

    }


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = exerciseList[position]
        holder.exerciseNameView.text = item.name
        holder.targetMuscleView.text = item.target

        holder.itemView.setOnClickListener {
            Log.d("HELPME","Listener set")
            holder.mExercise?.let { item ->
                mListener?.onItemClick(item)
            }
        }

        Glide.with(holder.itemView)
            .load(item.gifLink)
            .centerInside()
            .into(holder.exerciseGif)

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

