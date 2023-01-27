package com.example.movie.domain

class LoadMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke() = repository.loadMovies()
}