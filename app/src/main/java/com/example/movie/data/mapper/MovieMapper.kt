package com.example.movie.data.mapper

import com.example.movie.data.database.MovieInfoDbModel
import com.example.movie.data.network.model.MovieDto
import com.example.movie.domain.Movie

class MovieMapper {

    fun mapDtoToDbModel(dto: MovieDto) = MovieInfoDbModel(
        dto.id,
        dto.name,
        dto.year,
        dto.description,
        dto.poster.url,
        dto.rating.kp
    )

    fun mapDbModelToEntity(dbModel: MovieInfoDbModel) = Movie(
        dbModel.id,
        dbModel.name,
        dbModel.year,
        dbModel.description,
        dbModel.poster,
        dbModel.rating
    )

    fun mapDtoToEntity(dto: MovieDto) = Movie(
        dto.id,
        dto.name,
        dto.year,
        dto.description,
        dto.poster.url,
        dto.rating.kp
    )
}
