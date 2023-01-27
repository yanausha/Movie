package com.example.movie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.domain.GetMovieInfoUseCase
import com.example.movie.domain.GetMovieListUseCase
import com.example.movie.domain.LoadMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getMovieListUseCase = GetMovieListUseCase(repository)
    private val getMovieInfoUseCase = GetMovieInfoUseCase(repository)
    private val loadMoviesUseCase = LoadMoviesUseCase(repository)

    val movieList = getMovieListUseCase()
    fun getMovieInfo(movieId: Int) = getMovieInfoUseCase(movieId)

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase()
        }
    }

    fun isLoading() = repository.isLoading

    init {
        viewModelScope.launch {
            loadMoviesUseCase()
        }
    }
}
