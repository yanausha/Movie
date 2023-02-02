package com.example.movie.domain

data class GetFavoriteMovieListUseCase(
    private val repository: MovieRepository
) {

    operator fun invoke() = repository.getFavoriteMovieList()
}
