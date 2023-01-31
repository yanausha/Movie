package com.example.movie.domain

data class Review(
    val id: Int,
    val author: String?,
    val userRating: Double?,
    val type: String?,
    val review: String?
)
