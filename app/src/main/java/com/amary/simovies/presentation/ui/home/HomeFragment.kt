package com.amary.simovies.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.amary.simovies.R
import com.amary.simovies.presentation.adapter.TabAdapter
import com.amary.simovies.base.BaseFragment
import com.amary.simovies.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            viewPager.adapter = TabAdapter(requireActivity())
            TabLayoutMediator(tabs, viewPager){ tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object{
        private val TAB_TITLES = arrayOf(
            R.string.title_movies,
            R.string.title_tvseries
        )
    }
}