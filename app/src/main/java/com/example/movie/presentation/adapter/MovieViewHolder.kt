package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.MovieItemBinding

data class MovieViewHolder(
    var binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root)
