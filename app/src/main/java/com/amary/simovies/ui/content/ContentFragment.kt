package com.amary.simovies.ui.content

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
import com.amary.core.data.Resource
import com.amary.simovies.databinding.FragmentContentBinding
import com.amary.simovies.adapter.PagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : BaseFragment<FragmentContentBinding>(FragmentContentBinding::inflate) {
    private val viewModel: ContentViewModel by viewModel()
    private val idSection: Int by lazy { arguments?.getInt(ARG_SECTION_NUMBER) ?: 1 }
    private val adapter: PagerAdapter by lazy { PagerAdapter{
        val bundle = bundleOf(
            KeyValue.BUNDLE_DETAIL to it,
            KeyValue.BUNDLE_CONTENT to idSection
        )
        findNavController().navigate(R.id.navigation_detail, bundle)
    }}

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            rvContent.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvContent.adapter = adapter

            viewModel.content(idSection).observe(viewLifecycleOwner){
                adapter.submitData(lifecycle, it)
            }

            adapter.addLoadStateListener {
                viewModel.pagerResource(it).observe(viewLifecycleOwner){ state ->
                    when(state){
                        is Resource.Loading -> {
                            pbLoading.isVisible = true
                            rvContent.isVisible = false
                            tvNotFound.isVisible = false
                        }
                        is Resource.Success -> {
                            pbLoading.isVisible = false
                            rvContent.isVisible = true
                            tvNotFound.isVisible = false
                        }
                        is Resource.Failed -> {
                            pbLoading.isVisible = false
                            rvContent.isVisible = false
                            tvNotFound.isVisible = true
                        }
                    }
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