package com.amary.simovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amary.simovies.BuildConfig
import com.amary.simovies.R
import com.amary.simovies.databinding.ItemListBinding
import com.amary.core.domain.model.ResultData
import com.bumptech.glide.Glide

class PagerAdapter(private val itemClickListener: (ResultData) -> Unit) : PagingDataAdapter<ResultData, PagerAdapter.ViewHolder>(DiffCallBack) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(itemView: ItemListBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val binding = ItemListBinding.bind(itemView.root)

        init {
            binding.root.setOnClickListener{
                val story = getItem(absoluteAdapterPosition)
                story?.let { itemClickListener(it) }
            }
        }

        fun bind(item: ResultData) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(BuildConfig.BASE_IMAGE + item.posterPath)
                    .placeholder(ContextCompat.getDrawable(root.context, R.drawable.ic_place_holder))
                    .error(ContextCompat.getDrawable(root.context, R.drawable.ic_place_holder))
                    .into(imgPoster)

                txtTitle.text = item.title
            }
        }

    }

    companion object {
        object DiffCallBack : DiffUtil.ItemCallback<ResultData>(){
            override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData) = oldItem == newItem
        }
    }
}