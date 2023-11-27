package com.example.movieapp.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.MyApplication
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.remote.RetrofitInstance
import com.example.movieapp.data.models.MovieDetailsEntity
import com.example.movieapp.pesentation.MoviePagingSource
import com.example.movieapp.retrofit.MovieApi
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val context: Context) {

    private val retrofitInstance: MovieApi = RetrofitInstance.api
    private val movieDao: MovieDao = MyApplication.database.movieDao()

    fun getSearchResults(query: String): Flow<PagingData<MovieDetailsEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(retrofitInstance, movieDao, query, context) }
        ).flow
    }
}