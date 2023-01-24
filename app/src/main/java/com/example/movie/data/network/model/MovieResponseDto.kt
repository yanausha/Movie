package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("docs")
    val movies: List<MovieDto>? = null
)
