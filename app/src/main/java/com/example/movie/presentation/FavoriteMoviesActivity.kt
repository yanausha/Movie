package com.example.movie.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.databinding.ActivityFavoriteMoviesBinding
import com.example.movie.presentation.adapter.MovieAdapter

class FavoriteMoviesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFavoriteMoviesBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = MovieAdapter()
        with(binding) {
            recyclerViewFavoriteMovies.adapter = adapter
            recyclerViewFavoriteMovies.layoutManager =
                GridLayoutManager(this@FavoriteMoviesActivity, 2)
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.favoriteMovies.observe(this) {
            if (it != null) {
                adapter.submitList(it)
            }
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FavoriteMoviesActivity::class.java)
        }
    }
}