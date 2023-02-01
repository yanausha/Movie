package com.example.movie.data.mapper

import com.example.movie.data.database.FavoriteMovieDbModel
import com.example.movie.data.network.model.MovieDto
import com.example.movie.data.network.model.ReviewDto
import com.example.movie.data.network.model.TrailerDto
import com.example.movie.domain.Movie
import com.example.movie.domain.Review
import com.example.movie.domain.Trailer

class MovieMapper {
    fun mapMovieDtoToEntity(movie: MovieDto) = Movie(
        movie.id,
        movie.name,
        movie.year,
        movie.description,
        movie.poster.url,
        movie.rating.kp
    )

    fun mapTrailerDtoToEntity(movieId: Int, trailer: TrailerDto) = Trailer(
        movieId,
        trailer.name,
        trailer.url
    )

    fun mapReviewDtoToEntity(movieId: Int, review: ReviewDto) = Review(
        movieId,
        review.author,
        review.userRating,
        review.type,
        review.review
    )

    fun mapMovieDtoToDbModel(movie: MovieDto) = FavoriteMovieDbModel(
        movie.id,
        movie.name,
        movie.year,
        movie.description,
        movie.poster.url,
        movie.rating.kp
    )

    fun mapMovieDbModelToEntity(movie: FavoriteMovieDbModel) = Movie(
        movie.id,
        movie.name,
        movie.year,
        movie.description,
        movie.poster,
        movie.rating
    )
}
