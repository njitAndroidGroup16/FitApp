package com.group16.fitapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
import com.google.gson.reflect.TypeToken
import com.group16.fitapp.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val coroutineScope = CoroutineScope(Dispatchers.IO)
class ExerciseFragment:Fragment(R.layout.frag_exercise) {
    private val asyncHttpClient = AsyncHttpClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_exercise, container, false)

        val recyclerView = view.findViewById<View>(R.id.listOfExercises) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context,2)
        updateAdapter(recyclerView)
        return view
    }
    private fun updateAdapter(recyclerView: RecyclerView) {

            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://exercisedb.p.rapidapi.com/exercises?limit=5")
                .get()
                .addHeader("X-RapidAPI-Key", "cd2731863amshf24057c73da0478p108fe6jsn3303de79f3d4")
                .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        // Handle successful response
                        val responseBody = response.body?.string()
                        val gson = Gson()
                        Log.d("DEBUG", "Starting conversion the gson")
                        val arrayExerciseType = object : TypeToken<List<Exercise>>() {}.type
             //           val exerciseList = gson.fromJson(responseBody, Array<Exercise>::class.java).toList()
                        val exerciseList: List<Exercise> =
                            gson.fromJson(responseBody, arrayExerciseType)
                        val myAdapter = ExerciseAdapter(exerciseList)
                        activity?.runOnUiThread {
                            recyclerView.adapter = myAdapter
                        }

                        Log.d("BestSellerBooksFragment", "response successful")

                    } else {
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
    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Example HTTP request

    }
    */


}

