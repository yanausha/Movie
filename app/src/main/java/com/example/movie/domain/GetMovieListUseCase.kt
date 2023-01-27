package com.example.movie.domain

class GetMovieListUseCase(private val repository: MovieRepository) {

    operator fun invoke() = repository.getMovieList()
}
