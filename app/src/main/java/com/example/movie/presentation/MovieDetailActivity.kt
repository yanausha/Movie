package com.example.movie.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.domain.Trailer
import com.example.movie.presentation.adapter.ReviewAdapter
import com.example.movie.presentation.adapter.TrailerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val favoriteOff = ContextCompat.getDrawable(this, R.drawable.not_favorite)
        val favoriteOn = ContextCompat.getDrawable(this, R.drawable.add_favorite)

        val trailerAdapter = TrailerAdapter()
        binding.recyclerViewTrailers.adapter = trailerAdapter

        val reviewAdapter = ReviewAdapter()
        binding.recyclerViewReviews.adapter = reviewAdapter

        val movieId = intent.getIntExtra(EXTRA_MOVIE, 111)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        scope.launch {
            val movie = viewModel.getMovieInfo(movieId)

            with(binding) {
                Glide.with(this@MovieDetailActivity)
                    .load(movie.poster ?: R.drawable.noposter)
                    .into(imageViewPoster)
                textViewTitle.text = movie.name
                textViewYear.text = movie.year.toString()
                textViewDescription.text = movie.description
            }

            val trailers = viewModel.getTrailers(movieId)
            trailerAdapter.submitList(trailers)

            val review = viewModel.getReviews(movieId)
            reviewAdapter.submitList(review)

            viewModel.getFavoriteMovie(movieId).observe(this@MovieDetailActivity) {
                if (it == null) {
                    binding.imageViewSave.setImageDrawable(favoriteOff)
                    binding.imageViewSave.setOnClickListener {
                        viewModel.insertFavoriteMovie(movie)
                    }
                } else {
                    binding.imageViewSave.setImageDrawable(favoriteOn)
                    binding.imageViewSave.setOnClickListener {
                        viewModel.deleteFavoriteMovie(movieId)
                    }
                }
            }
        }

        trailerAdapter.onTrailerClickListener = object : TrailerAdapter.OnTrailerClickListener {

            override fun onTrailerClick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {

        private const val EXTRA_MOVIE = "movie"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movieId)
            return intent
        }
    }
}
