package com.example.movieapp.pesentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.MovieDetailsEntity
import com.example.movieapp.databinding.MovieItemListBinding

class MovieAdapter : PagingDataAdapter<MovieDetailsEntity, MovieAdapter.MovieViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(private val binding: MovieItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(search: MovieDetailsEntity) {
            Glide.with(binding.poster)
                .load(search.Poster)
                .into(binding.poster)

            binding.title.text = search.Title
            binding.year.text = search.Year
            binding.type.text = search.Type
            binding.imdbId.text = search.imdbID
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<MovieDetailsEntity>() {
        override fun areItemsTheSame(oldItem: MovieDetailsEntity, newItem: MovieDetailsEntity): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: MovieDetailsEntity, newItem: MovieDetailsEntity): Boolean {
            return oldItem == newItem
        }
    }
}