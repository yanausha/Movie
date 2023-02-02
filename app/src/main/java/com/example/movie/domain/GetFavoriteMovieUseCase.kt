package com.example.movie.domain

data class GetFavoriteMovieUseCase(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int) = repository.getFavoriteMovie(id)
}
