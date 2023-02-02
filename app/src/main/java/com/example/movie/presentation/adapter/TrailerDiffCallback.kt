package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movie.domain.Trailer

object TrailerDiffCallback : DiffUtil.ItemCallback<Trailer>() {

    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}
