package com.amary.simovies.presentation.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amary.simovies.presentation.ui.content.ContentFragment

class TabAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = ContentFragment.newInstance(position + 1)
}