package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewResponseDto(
    @SerializedName("docs")
    val reviews: List<ReviewDto>
)
