package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movie.databinding.TrailerItemBinding
import com.example.movie.domain.Trailer

class TrailerAdapter : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback) {

    var onTrailerClickListener: OnTrailerClickListener? = null

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
        holder.binding.textViewTrailerName.text = trailer.name

        holder.binding.root.setOnClickListener {
            onTrailerClickListener?.onTrailerClick(trailer)
        }
    }

    interface OnTrailerClickListener {
        fun onTrailerClick(trailer: Trailer)
    }
}
