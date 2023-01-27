package com.example.movie.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieInfoDao {

    @Query("SELECT * FROM favorite_movies")
    fun getMovieList(): LiveData<List<MovieInfoDbModel>>

    @Query("SELECT * FROM favorite_movies WHERE id == :id")
    fun getMovieInfo(id: Int): LiveData<MovieInfoDbModel>

    @Insert
    suspend fun insertMovie(movie: MovieInfoDbModel)

    @Query("DELETE FROM favorite_movies WHERE id == :id")
    suspend fun deleteMovie(id: Int)
}