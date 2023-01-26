package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.network.model.MovieDto
import com.example.movie.databinding.MovieItemBinding

class MovieAdapter : ListAdapter<MovieDto, MovieViewHolder>(MovieDiffCallback) {

    var onScrollListener: OnScrollListener? = null

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
        val rating = movie.rating.kp ?: 0.00

        try {
            Glide.with(holder.itemView)
                .load(movie.poster.url)
                .into(holder.binding.imageViewPoster)
        } catch (e: Exception) {
            holder.binding.imageViewPoster.setBackgroundResource(R.drawable.noposter)
        }

        holder.binding.textViewRating.text = String.format("%.2f", movie.rating.kp)

        with(holder.binding) {
            when (rating) {
                in 7.5..10.0 -> textViewRating.setBackgroundResource(R.drawable.circle_super)
                in 5.5..7.5 -> textViewRating.setBackgroundResource(R.drawable.circle_good)
                else -> textViewRating.setBackgroundResource(R.drawable.circle_bad)
            }
        }

        if (position >= this.currentList.size - 10) onScrollListener?.onScrollListEnd()
    }

    interface OnScrollListener {
        fun onScrollListEnd()
    }
}
