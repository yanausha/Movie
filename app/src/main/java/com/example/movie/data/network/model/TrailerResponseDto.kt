package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailerResponseDto(
    @SerializedName("videos")
    val videos: TrailerListDto
)
