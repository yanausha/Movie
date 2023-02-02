package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movie.domain.Review

object ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}
