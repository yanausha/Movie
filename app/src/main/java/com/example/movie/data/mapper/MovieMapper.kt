package com.example.movie.data.mapper

import com.example.movie.data.network.model.MovieDto
import com.example.movie.data.network.model.TrailerDto
import com.example.movie.domain.Movie
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
    fun mapTrailerDtoToEntity(trailer: TrailerDto) = Trailer(
        trailer.name,
        trailer.url
    )
}
