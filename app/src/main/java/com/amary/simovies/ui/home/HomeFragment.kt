package com.amary.simovies.ui.home

import android.os.Bundle
import android.view.View
import com.amary.simovies.R
import com.amary.simovies.adapter.TabAdapter
import com.amary.core.base.BaseFragment
import com.amary.simovies.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private var tabAdapter: TabAdapter? = null

    override fun initView(view: View, savedInstanceState: Bundle?) {
        tabAdapter = TabAdapter(this)
        binding?.apply {
            viewPager.isSaveEnabled = false
            viewPager.adapter = tabAdapter
            TabLayoutMediator(tabs, viewPager){ tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        binding?.viewPager?.adapter = null
        tabAdapter = null
    }

    companion object{
        private val TAB_TITLES = arrayOf(
            R.string.title_movies,
            R.string.title_tvseries
        )
    }
}