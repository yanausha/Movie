package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class RatingDto (
    @SerializedName("kp")
    val kp: Double? = null
)
