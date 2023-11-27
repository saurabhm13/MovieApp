package com.example.movieapp.data.models

data class MovieResponse(
    val Response: String,
    val Search: List<MovieDetailsEntity>,
    val totalResults: String
)