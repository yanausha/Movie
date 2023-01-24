package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movie.data.network.model.MovieDto

object MovieDiffCallback : DiffUtil.ItemCallback<MovieDto>() {

    override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem == newItem
    }
}
