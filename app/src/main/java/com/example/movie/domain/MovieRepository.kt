package com.example.movie.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    suspend fun getMovieInfo(movieId: Int): Movie

    suspend fun loadMovies()

    suspend fun getTrailers(movieId: Int): List<Trailer>

    suspend fun getReviews(movieId: Int): List<Review>

    suspend fun insertFavoriteMovie(movie: Movie)

    fun getFavoriteMovie(movieId: Int): LiveData<Movie?>

    fun getFavoriteMovieList(): LiveData<List<Movie?>>

    suspend fun deleteFavoriteMovie(movieId: Int)
}
