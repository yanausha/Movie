package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.TrailerItemBinding

data class TrailerViewHolder (
    val binding: TrailerItemBinding
): RecyclerView.ViewHolder(binding.root)