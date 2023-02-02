package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("userRating")
    val userRating: Double?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("review")
    val review: String?
)
