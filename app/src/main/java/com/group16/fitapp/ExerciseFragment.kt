package com.group16.fitapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.loopj.android.http.AsyncHttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class ExerciseFragment:Fragment(R.layout.frag_exercise) {
    private val asyncHttpClient = AsyncHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myText = view.findViewById<TextView>(R.id.AllExercises)
        // Example HTTP request
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://exercisedb.p.rapidapi.com/exercises")
            .get()
            .addHeader("X-RapidAPI-Key", "cd2731863amshf24057c73da0478p108fe6jsn3303de79f3d4")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()

        //val response = client.newCall(request).execute()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("CONTENT", "We got it going")
                    // Handle successful response

                    val responseBody = response.body?.string()
                    if (response.header("Content-Type") != null) {
                        val typeOfReturn = response.header("Content-Type").toString()
                        Log.d("CONTENT", typeOfReturn)
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
