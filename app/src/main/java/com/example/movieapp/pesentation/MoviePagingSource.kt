package com.example.movieapp.pesentation

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.NetworkUtils
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.models.MovieDetailsEntity
import com.example.movieapp.retrofit.MovieApi
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: MovieApi,
    private val movieDao: MovieDao,
    private val query: String,
    private val context: Context,
) : PagingSource<Int, MovieDetailsEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailsEntity> {
        return try {
            val page = params.key ?: 1
            val apiKey = "5d81e1ce"

            val movies = if (NetworkUtils.isNetworkAvailable(context)) {
                api.searchMovies("movie", apiKey, page, query).body()?.Search ?: emptyList()
            } else {

                movieDao.getAllMovies(query)
            }

            movieDao.insertAll(movies.map { it})

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDetailsEntity>): Int? {
        TODO("Not yet implemented")
    }
}
