package com.example.movie.domain

class GetTrailersUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int) = repository.getTrailers(id)
}
