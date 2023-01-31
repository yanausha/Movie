package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailerListDto(
    @SerializedName("trailers")
    val trailers: List<TrailerDto>
)
