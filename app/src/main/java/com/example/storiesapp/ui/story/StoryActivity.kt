package com.example.storiesapp.ui.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.storiesapp.R
import com.example.storiesapp.databinding.ActivityStoryBinding
import jp.shts.android.storiesprogressview.StoriesProgressView

class StoryActivity : AppCompatActivity(){
    private val viewModel by viewModels<StoryViewModel>()
    private val adapter = StoryAdapter()
    private lateinit var binding: ActivityStoryBinding
    private val pageChangeCallback = StoryPageChangeCallBack()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val imagePosition = intent.getStringExtra("image")

//        imagePosition?.let {
//            viewModel.getImage(position = imagePosition)
//            binding.storyImageView.adapter = adapter
//            binding.storyImageView.registerOnPageChangeCallback(pageChangeCallback)
//        }

        viewModel.getImages()
        binding.storyImageViewpager.adapter = adapter
        binding.storyImageViewpager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private val nextPageRunnable = Runnable {
        binding.storyImageViewpager.currentItem++
    }

    inner class StoryPageChangeCallBack: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.statusProgress.scaleX = 0F
            binding.statusProgress.animate()
                .scaleX(1F)
                .setDuration(5000)
                .start()

            handler.removeCallbacks(nextPageRunnable)
            handler.postDelayed(nextPageRunnable, 5000)
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.images.observe(this) {
            adapter.submitList(it)
        }
    }
}