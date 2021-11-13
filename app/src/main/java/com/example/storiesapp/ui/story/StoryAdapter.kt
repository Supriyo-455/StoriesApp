package com.example.storiesapp.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.storiesapp.R
import com.example.storiesapp.databinding.StoryListItemBinding

class StoryAdapter :
    ListAdapter<String, StoryAdapter.StoryPageViewHolder>(StoryDiffUtilItemCallBack()){
    class StoryPageViewHolder(
        val binding: StoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class StoryDiffUtilItemCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPageViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = StoryListItemBinding.inflate(inflater, parent, false)
        return StoryPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryPageViewHolder, position: Int) {
        val image = getItem(position)
        image?.let {
            holder.binding.ivStoryItem.load(image){
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_broken_image_24)
            }
        }
    }


}