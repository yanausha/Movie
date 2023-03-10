package com.example.movie.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.domain.Movie
import com.example.movie.presentation.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = MovieAdapter()
        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movieList.observe(this) {
            adapter.submitList(it)
        }
        viewModel.isLoading().observe(this) {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }

        adapter.onScrollListener = object : MovieAdapter.OnScrollListener {
            override fun onScrollListEnd() {
                viewModel.loadMovies()
            }
        }

        adapter.onClickMovieListener = object : MovieAdapter.OnClickMovieListener {
            override fun onClickMovie(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@MainActivity, movie.id)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuFavoriteMovies) {
            val intent = FavoriteMoviesActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
