package com.example.movieapp

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.MovieDatabase

class MyApplication : Application() {

    companion object {
        lateinit var database: MovieDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, "movie_database"
        ).build()
    }
}