package com.group16.fitapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.group16.fitapp.Exercise
class ExerciseFragment:Fragment(R.layout.frag_exercise) {
    private val asyncHttpClient = AsyncHttpClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_exercise, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.listOfExercises)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        val items = listOf(
            Exercise(),
            Exercise(),
            Exercise(),
            // Add more items as needed
        )

        val adapter = ExerciseAdapter(items)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Example HTTP request
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://exercisedb.p.rapidapi.com/exercises?limit=5")
            .get()
            .addHeader("X-RapidAPI-Key", "cd2731863amshf24057c73da0478p108fe6jsn3303de79f3d4")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()

        //val response = client.newCall(request).execute()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    // Handle successful response

                    val responseBody = response.body?.string()
                    val gson = Gson()
                    Log.d("DEBUG","Starting conversion the gson")
                    val exerciseList = gson.fromJson(responseBody, Array<Exercise>::class.java).toList()
                    for (exercise in exerciseList){
                        Log.d("ARRAYCONTENTS",exercise.name.toString())
                        Log.d("ARRAYCONTENTS",exercise.target.toString())
                        Log.d("ARRAYCONTENTS",exercise.steps.toString())
                    }

                    //myText.text = responseBody
                    // Process the response data here
                } else {
                    // Handle unsuccessful response (e.g., HTTP error codes)
                    // You can access the HTTP status code using response.code()
                    // Example: Log the error message
                    Log.e("API Call", "HTTP Error: ${response.code} - ${response.message}")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle network failure or other exceptions
                Log.e("API Call", "Network Error: ${e.message}")
                // You can also notify the user about the error using a Toast or Snackbar
            }
        })
    }


}
