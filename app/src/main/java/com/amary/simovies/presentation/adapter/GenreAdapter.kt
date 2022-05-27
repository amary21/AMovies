package com.amary.simovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amary.simovies.databinding.ItemGenreBinding
import com.amary.simovies.domain.model.Genre

class GenreAdapter: ListAdapter<Genre, GenreAdapter.ViewHolder>(DiffCallBack) {
    companion object {
        object DiffCallBack : DiffUtil.ItemCallback<Genre>(){
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre) = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: Genre, newItem: Genre) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: ItemGenreBinding): RecyclerView.ViewHolder(itemView.root) {
        private val binding = ItemGenreBinding.bind(itemView.root)
        fun bind(data: Genre) {
            binding.txtGenre.text = data.name
        }

    }
}