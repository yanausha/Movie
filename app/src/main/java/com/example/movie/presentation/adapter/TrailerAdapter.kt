package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movie.databinding.TrailerItemBinding
import com.example.movie.domain.Trailer

class TrailerAdapter : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val binding = TrailerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)
        with(holder.binding) {
            textViewTrailerName.text = trailer.name
        }
    }
}
