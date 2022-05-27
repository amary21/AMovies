package com.amary.simovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amary.simovies.BuildConfig
import com.amary.simovies.databinding.ItemScreenshotBinding
import com.amary.simovies.domain.model.Backdrop
import com.bumptech.glide.Glide

class ScreenShotAdapter: ListAdapter<Backdrop, ScreenShotAdapter.ViewHolder>(DiffCallBack) {
    companion object {
        object DiffCallBack : DiffUtil.ItemCallback<Backdrop>(){
            override fun areItemsTheSame(oldItem: Backdrop, newItem: Backdrop) = oldItem.filePath == newItem.filePath
            override fun areContentsTheSame(oldItem: Backdrop, newItem: Backdrop) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemScreenshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: ItemScreenshotBinding): RecyclerView.ViewHolder(itemView.root) {
        private val binding = ItemScreenshotBinding.bind(itemView.root)
        fun bind(data: Backdrop) {
            Glide.with(binding.root.context)
                .load(BuildConfig.BASE_IMAGE + data.filePath)
                .into(binding.imgScreenshot)
        }

    }
}