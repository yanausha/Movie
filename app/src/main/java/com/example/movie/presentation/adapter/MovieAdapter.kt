package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.movie.data.network.model.MovieDto
import com.example.movie.databinding.MovieItemBinding

class MovieAdapter : ListAdapter<MovieDto, MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = getItem(position)
        Glide.with(holder.itemView)
            .load(movie.poster.url)
            .into(holder.binding.imageViewPoster)

        holder.binding.textViewRating.text = String.format("%.2f", movie.rating.kp)

    }
}
