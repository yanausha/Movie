package com.example.movie.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_movies")
data class MovieInfoDbModel(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val year: Int?,
    val description: String?,
    val poster: String?,
    val rating: Double?
)
