package com.group16.fitapp

import com.google.gson.annotations.SerializedName

class Exercise {
    @JvmField
    @SerializedName("name")
    var name: String? = null

    @JvmField
    @SerializedName("target")
    var target: String? = null

    @JvmField
    @SerializedName("gifUrl")
    var gifLink: String? = null

    @JvmField
    @SerializedName("instructions")
    val steps: Array<String> = arrayOf()
}