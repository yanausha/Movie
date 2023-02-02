package com.example.movie.domain

data class InsertFavoriteMovieUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) = repository.insertFavoriteMovie(movie)
}
