package com.example.movie.domain

class GetMovieInfoUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId: Int) = repository.getMovieInfo(movieId)
}