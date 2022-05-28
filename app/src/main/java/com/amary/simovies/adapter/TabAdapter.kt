package com.amary.simovies.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amary.simovies.ui.content.ContentFragment

class TabAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = ContentFragment.newInstance(position + 1)
}