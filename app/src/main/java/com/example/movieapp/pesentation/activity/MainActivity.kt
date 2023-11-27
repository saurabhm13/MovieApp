package com.example.movieapp.pesentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.pesentation.adapter.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MovieAdapter()
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, SearchViewModelFactory(this)).get(SearchViewModel::class.java)

        prepareRecyclerView()

        binding.search.addTextChangedListener { editable ->
            if (editable?.length!! >= 3) {
                // Call the API when the user types 3 characters
                lifecycleScope.launch {
                    viewModel.searchMovies(editable.toString()).collectLatest {
                        movieAdapter.submitData(it)
                    }
                }
            }
        }

    }

    private fun prepareRecyclerView() {

        binding.resultRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }

    }
}