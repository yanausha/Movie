package com.example.movie.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.movie.data.mapper.MovieMapper
import com.example.movie.data.network.ApiFactory
import com.example.movie.data.network.model.MovieDto
import com.example.movie.domain.Movie
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.Review
import com.example.movie.domain.Trailer
import kotlinx.coroutines.delay

class MovieRepositoryImpl(private val application: Application) : MovieRepository {

    private val apiService = ApiFactory.apiService

    private var movies = MutableLiveData<List<MovieDto>>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var page = 1

    private val mapper = MovieMapper()

    override fun getMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movies) {
            it.map {
                mapper.mapMovieDtoToEntity(it)
            }
        }
    }

    override suspend fun getMovieInfo(movieId: Int): Movie {

        val movie = apiService.getMovie(movieId)
        return mapper.mapMovieDtoToEntity(movie)
    }

    override suspend fun loadMovies() {

        if (checkLoadingMovies()) return

        _isLoading.value = true
        delay(5000)
        val loadedMovies = movies.value?.toMutableList()
        if (loadedMovies != null) {
            val newMovies = apiService.getMovies(page)
            newMovies.movies?.let { loadedMovies.addAll(it) }
            movies.value = loadedMovies
        } else {
            movies.value = apiService.getMovies(page).movies
        }
        _isLoading.value = false
        page++
    }

    override suspend fun getTrailers(movieId: Int): List<Trailer> {
        val trailer = apiService.getTrailers(movieId).videos
        return trailer.trailers.map {
            mapper.mapTrailerDtoToEntity(movieId, it)
        }
    }

    override suspend fun getReviews(movieId: Int): List<Review> {
        val review = apiService.getReviews(movieId)
        return review.reviews.map {
            mapper.mapReviewDtoToEntity(movieId, it)
        }
    }

    private fun checkLoadingMovies(): Boolean {
        val noMore = isLoading.value
        return noMore != null && noMore
    }
}
