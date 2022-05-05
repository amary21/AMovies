package com.amary.simovies.ui.content

import android.os.Bundle
import android.view.View
import com.amary.simovies.base.BaseFragment
import com.amary.simovies.databinding.FragmentContentBinding

class ContentFragment : BaseFragment<FragmentContentBinding>(FragmentContentBinding::inflate) {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        //TODO("Not yet implemented")
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