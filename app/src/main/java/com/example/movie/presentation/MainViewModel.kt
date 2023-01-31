package com.example.movie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.domain.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getMovieListUseCase = GetMovieListUseCase(repository)
    private val getMovieInfoUseCase = GetMovieInfoUseCase(repository)
    private val loadMoviesUseCase = LoadMoviesUseCase(repository)
    private val getTrailersUseCase = GetTrailersUseCase(repository)
    private val getReviewUseCase = GetReviewUseCase(repository)

    val movieList = getMovieListUseCase()

    suspend fun getMovieInfo(movieId: Int): Movie {
        return getMovieInfoUseCase(movieId)
    }

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase()
        }
    }

    fun isLoading() = repository.isLoading

    suspend fun getTrailers(movieId: Int): List<Trailer> {
        return getTrailersUseCase(movieId)
    }

    suspend fun getReviews(movieId: Int): List<Review> {
        return getReviewUseCase(movieId)
    }

    init {
        viewModelScope.launch {
            loadMoviesUseCase()
        }
    }
}
