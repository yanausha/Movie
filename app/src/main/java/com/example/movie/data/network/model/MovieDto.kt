package com.example.movie.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_movies")
data class MovieDto(
    @PrimaryKey
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
