package com.example.storiesapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.storiesapp.databinding.StoriesListItemBinding
import com.example.storiesapp.ui.story.StoryActivity

class StoriesAdapter : ListAdapter<String, StoriesAdapter.StorysViewHolder>(StoriesDiffCallBack()) {
    class StorysViewHolder(
        val binding: StoriesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private class StoriesDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorysViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = StoriesListItemBinding.inflate(inflater, parent, false)

        return StorysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StorysViewHolder, position: Int) {
        val image = getItem(position)
        holder.binding.tvStoryHead.text = "Image${position}"
        holder.binding.ivStoryHead.load(image)

        holder.binding.root.apply {
            setOnClickListener {
                val intent = Intent(context, StoryActivity::class.java).apply {
                    //putExtra("image", position)
                }
                context.startActivity(intent)
            }
        }
    }
}