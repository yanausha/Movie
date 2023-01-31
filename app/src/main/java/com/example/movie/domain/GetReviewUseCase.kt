package com.example.movie.domain

class GetReviewUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int) = repository.getReviews(id)
}
