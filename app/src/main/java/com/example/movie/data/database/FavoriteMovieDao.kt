package com.example.movie.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovieList(): LiveData<List<FavoriteMovieDbModel?>>

    @Query("SELECT * FROM favorite_movies WHERE id == :id")
    fun getFavoriteMovieInfo(id: Int): LiveData<FavoriteMovieDbModel?>

    @Insert
    suspend fun insertFavoriteMovie(movie: FavoriteMovieDbModel)

    @Query("DELETE FROM favorite_movies WHERE id == :id")
    suspend fun deleteFavoriteMovie(id: Int)
}
