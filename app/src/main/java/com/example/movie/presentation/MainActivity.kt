package com.example.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.databinding.ActivityMainBinding
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
        viewModel.movies.observe(this) {
            adapter.submitList(it)
        }
        viewModel.isLoading.observe(this) {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }
        viewModel.loadMovies()

        adapter.onScrollListener = object : MovieAdapter.OnScrollListener {
            override fun onScrollListEnd() {
                viewModel.loadMovies()
            }
        }
    }
}
