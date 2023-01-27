package com.example.movie.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    fun getMovieInfo(movieId: Int): LiveData<Movie>
}