package com.example.movie.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.network.ApiFactory
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.domain.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(EXTRA_MOVIE, Movie::class.java)
            } else {
                intent.getSerializableExtra(EXTRA_MOVIE) as Movie
            }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movieList.observe(this) {
            it.map {
                with(binding) {
                    Glide.with(this@MovieDetailActivity)
                        .load(movie?.poster ?: R.drawable.noposter)
                        .into(imageViewPoster)
                    textViewTitle.text = movie?.name
                    textViewYear.text = movie?.year.toString()
                    textViewDescription.text = movie?.description
                }
            }
        }

        scope.launch {
            val trailers = ApiFactory.apiService.getTrailers(movie?.id ?: 0)
            Log.d("Detail", trailers.toString())
        }
    }

    companion object {

        private const val EXTRA_MOVIE = "movie"

        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }
}
