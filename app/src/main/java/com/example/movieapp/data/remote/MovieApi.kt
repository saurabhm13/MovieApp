package com.example.movieapp.retrofit

import com.example.movieapp.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("type") type: String,
        @Query("apikey") apiKey: String,
        @Query("page") page: Int,
        @Query("s") query: String
    ): Response<MovieResponse>
}