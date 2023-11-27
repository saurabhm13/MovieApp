package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.models.MovieDetailsEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDetailsEntity>)

    @Query("SELECT * FROM moviedetailsentity " +
            "WHERE LOWER(title) LIKE '%' || LOWER(:title) || '%' OR\n" +
            "UPPER(:title) == title")
    suspend fun getAllMovies(title: String): List<MovieDetailsEntity>
}