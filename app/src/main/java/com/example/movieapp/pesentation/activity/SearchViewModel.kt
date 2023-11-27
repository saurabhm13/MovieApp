package com.example.movieapp.pesentation.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.models.MovieDetailsEntity
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchViewModel(context: Context): ViewModel() {

    private val repository: MovieRepository = MovieRepository(context)

    fun searchMovies(query: String): Flow<PagingData<MovieDetailsEntity>> {
        return repository.getSearchResults(query)
            .cachedIn(viewModelScope)
    }

}