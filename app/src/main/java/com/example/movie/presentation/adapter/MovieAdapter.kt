package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.databinding.MovieItemBinding
import com.example.movie.domain.Movie

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback) {

    var onClickMovieListener: OnClickMovieListener? = null

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
        val rating = movie.rating ?: 0.00

        try {
            Glide.with(holder.itemView)
                .load(movie.poster)
                .into(holder.binding.imageViewPoster)
        } catch (e: Exception) {
            holder.binding.imageViewPoster.setBackgroundResource(R.drawable.noposter)
        }

        holder.binding.textViewRating.text = String.format("%.2f", movie.rating)

        with(holder.binding) {
            when (rating) {
                in 7.5..10.0 -> textViewRating.setBackgroundResource(R.drawable.circle_super)
                in 5.5..7.5 -> textViewRating.setBackgroundResource(R.drawable.circle_good)
                else -> textViewRating.setBackgroundResource(R.drawable.circle_bad)
            }
        }

        if (position >= this.currentList.size - 10) {
            onScrollListener?.onScrollListEnd()
        }

        holder.binding.root.setOnClickListener {
            onClickMovieListener?.onClickMovie(movie)
        }
    }

    interface OnScrollListener {
        fun onScrollListEnd()
    }

    interface OnClickMovieListener {
        fun onClickMovie(movie: Movie)
    }
}
