package com.amary.simovies.presentation.ui.content

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amary.simovies.base.BaseFragment
import com.amary.simovies.databinding.FragmentContentBinding
import com.amary.simovies.presentation.adapter.PagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : BaseFragment<FragmentContentBinding>(FragmentContentBinding::inflate) {
    private val viewModel: ContentViewModel by viewModel()
    private val adapter: PagerAdapter by lazy { PagerAdapter{

    } }
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            rvContent.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvContent.adapter = adapter

            viewModel.content(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1).observe(viewLifecycleOwner){
                adapter.submitData(lifecycle, it)
            }

            adapter.addLoadStateListener {
                viewModel.pagerResource(it).observe(viewLifecycleOwner){ state ->
                    Log.e("initView", state.message.toString())
                }
            }
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ContentFragment {
            return ContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}