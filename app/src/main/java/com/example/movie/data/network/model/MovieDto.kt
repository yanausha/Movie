package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("year")
    val year: Int? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("poster")
    val poster: PosterDto,
    @SerializedName("rating")
    val rating: RatingDto
)
