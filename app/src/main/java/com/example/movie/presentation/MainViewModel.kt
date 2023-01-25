package com.example.movie.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.network.ApiFactory
import com.example.movie.data.network.model.MovieDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = ApiFactory.apiService

    private val _movies = MutableLiveData<List<MovieDto>>()
    val movies: LiveData<List<MovieDto>> = _movies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var page = 1

    init {
        loadMovies()
    }

    fun loadMovies() {

        viewModelScope.launch {
            if (checkLoadingMovies()) return@launch

            _isLoading.value = true
            delay(5000)
            addMoviesToEnd()
            _isLoading.value = false
//            noMore = true
            Log.d("ViewModel", page.toString())
            page++
        }
    }

    private fun checkLoadingMovies(): Boolean {
        val noMore = isLoading.value
        return noMore != null && noMore
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
