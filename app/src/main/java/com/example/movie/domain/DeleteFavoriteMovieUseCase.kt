package com.example.movie.domain

data class DeleteFavoriteMovieUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int) = repository.deleteFavoriteMovie(id)
}
