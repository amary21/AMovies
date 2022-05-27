package com.amary.simovies.bookmark.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amary.simovies.R
import com.amary.core.base.BaseFragment
import com.amary.core.constant.KeyValue
import com.amary.simovies.adapter.FavoriteAdapter
import com.amary.simovies.bookmark.databinding.FragmentBookmarkBinding
import com.amary.simovies.bookmark.di.bookmarkModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {
    private val viewModel: BookmarkViewModel by viewModel()
    private val favoriteAdapter: FavoriteAdapter by lazy { FavoriteAdapter{
        val bundle = bundleOf(
            KeyValue.BUNDLE_DETAIL to it,
            KeyValue.BUNDLE_CONTENT to it.idContent
        )
        findNavController().navigate(R.id.navigation_detail, bundle)
    }}

    override fun onLoadModuleDFM() { loadKoinModules(bookmarkModule) }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            rvContent.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvContent.adapter = favoriteAdapter

            viewModel.allFavorite.observe(viewLifecycleOwner){
                if (it.isNotEmpty()){
                    tvNotFound.isVisible = false
                    rvContent.isVisible = true
                    favoriteAdapter.submitList(it)
                } else {
                    tvNotFound.isVisible = true
                    rvContent.isVisible = false
                }
            }
        }
    }
}