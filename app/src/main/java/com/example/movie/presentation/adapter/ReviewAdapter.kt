package com.example.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movie.R
import com.example.movie.databinding.ReviewItemBinding
import com.example.movie.domain.Review

class ReviewAdapter : ListAdapter<Review, ReviewViewHolder>(ReviewDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)
        val type = review.type
        with(holder.binding) {

            when (type) {
                REVIEW_POSITIVE_TYPE -> cardViewReview.setBackgroundResource(R.color.green_85)
                REVIEW_NEUTRAL_TYPE -> cardViewReview.setBackgroundResource(R.color.yellow_85)
                REVIEW_NEGATIVE_TYPE -> cardViewReview.setBackgroundResource(R.color.orange_85)
            }

            textViewUser.text = review.author
            textViewUserRating.text = if (review.userRating != 0.0) {
                String.format("%d", review.userRating?.toInt())
            } else {
                NO_USER_RATING
            }
            textViewReview.text = review.review
        }
    }

    companion object {

        private const val REVIEW_POSITIVE_TYPE = "Позитивный"
        private const val REVIEW_NEUTRAL_TYPE = "Нейтральный"
        private const val REVIEW_NEGATIVE_TYPE = "Негативный"

        private const val NO_USER_RATING = ""
    }
}
