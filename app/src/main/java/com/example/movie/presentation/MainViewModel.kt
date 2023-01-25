package com.example.movie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.network.ApiFactory
import com.example.movie.data.network.model.MovieDto
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = ApiFactory.apiService

    private val _movies = MutableLiveData<List<MovieDto>>()
    val movies: LiveData<List<MovieDto>> = _movies

    private var page = 1

    fun loadMovies() {
        viewModelScope.launch {
            addMoviesToEnd()
            page++
        }
    }

    private suspend fun addMoviesToEnd() {
        val loadedMovies = movies.value?.toMutableList()
        if (loadedMovies != null) {
            movies.value?.let {
                loadedMovies.addAll(it)
                _movies.value = loadedMovies
            }
        } else {
            _movies.value = apiService.getMovies(page).movies
        }
    }
}
