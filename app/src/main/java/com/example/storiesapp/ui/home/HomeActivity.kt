package com.example.storiesapp.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.storiesapp.R
import com.example.storiesapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = StoriesAdapter()
    private val viewModel by viewModels<StoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStories.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        viewModel.fetchImages()
        binding.rvStories.adapter = adapter

        setupNav()
    }

    private fun setupNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id
            .nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.navView

        navView.setupWithNavController(navController)
    }


    override fun onResume() {
        super.onResume()
        viewModel.images.observe(this) {
            it.forEach { image ->
                val request = ImageRequest.Builder(this)
                    .data(image)
                    .size(resources.getDimensionPixelSize(R.dimen.story_icon_size))
                    .build()
                imageLoader.enqueue(request)
            }
            adapter.submitList(it)
        }
    }
}