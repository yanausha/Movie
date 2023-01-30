package com.example.movie.domain

data class Movie(
    val id: Int,
    val name: String?,
    val year: Int?,
    val description: String?,
    val poster: String?,
    val rating: Double?
): java.io.Serializable
