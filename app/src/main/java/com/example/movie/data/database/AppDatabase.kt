package com.example.movie.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovieDbModel::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var database: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                return instance
            }
        }
    }

    abstract fun favoriteMovieInfoDao(): FavoriteMovieDao
}
