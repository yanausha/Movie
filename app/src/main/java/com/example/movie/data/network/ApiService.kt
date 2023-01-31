package com.example.movie.data.network

import com.example.movie.data.network.model.MovieResponseDto
import com.example.movie.data.network.model.TrailerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie?token=2EA18DT-MHPMKV6-HJEPKT6-3G6BTKJ&field=rating.kp&search=5-10&sortField=votes.kp&sortType=-1&limit=20")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieResponseDto

    @GET("movie?token=2EA18DT-MHPMKV6-HJEPKT6-3G6BTKJ&field=id")
    suspend fun getTrailers(
        @Query("search") id: Int
    ): TrailerResponseDto
}
