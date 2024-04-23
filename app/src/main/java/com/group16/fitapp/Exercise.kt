package com.group16.fitapp

import com.google.gson.annotations.SerializedName

class Exercise {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("target")
    var target: String? = null

    @SerializedName("gifUrl")
    var gifLink: String? = null

    @SerializedName("instructions")
    val steps: Array<String> = arrayOf()
}