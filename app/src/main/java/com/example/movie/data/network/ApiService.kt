package com.example.movie.data.network

import com.example.movie.data.network.model.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie?token=XMHZ37R-C9RMRAY-PKSH04M-8RW8FFE&field=rating.kp&search=5-10&sortField=votes.kp&sortType=-1&limit=20")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieResponseDto
}
