package com.example.movieapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailsEntity(
    @PrimaryKey val imdbID: String,
    val Title: String,
    val Year: String,
    val Type: String,
    val Poster: String
)