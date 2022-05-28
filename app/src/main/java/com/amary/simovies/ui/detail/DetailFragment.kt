package com.amary.simovies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amary.simovies.BuildConfig
import com.amary.simovies.R
import com.amary.core.base.BaseFragment
import com.amary.core.constant.KeyValue
import com.amary.core.data.Resource
import com.amary.simovies.databinding.FragmentDetailBinding
import com.amary.core.domain.model.ResultData
import com.amary.simovies.adapter.GenreAdapter
import com.amary.simovies.adapter.ScreenShotAdapter
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModel()
    private val resultData: ResultData? by lazy { arguments?.getSerializable(KeyValue.BUNDLE_DETAIL) as ResultData }
    private val idContent: Int by lazy { arguments?.getInt(KeyValue.BUNDLE_CONTENT) ?: 1 }
    private var genreAdapter: GenreAdapter? = null
    private var screenShotAdapter: ScreenShotAdapter? = null

    override fun initView(view: View, savedInstanceState: Bundle?) {
        genreAdapter = GenreAdapter()
        screenShotAdapter = ScreenShotAdapter()

        binding?.apply {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            rvGenre.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvGenre.adapter = genreAdapter

            rvScreenshot.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvScreenshot.adapter = screenShotAdapter

            Glide.with(requireContext())
                .load(BuildConfig.BASE_IMAGE + resultData?.backdropPath)
                .into(imgPoster)

            txtTitle.text = resultData?.title
            txtRate.text = resultData?.voteAverage.toString()
            txtRelease.text = viewModel.convertDateFormat(resultData?.releaseDate.toString())
            txtSynopsis.text = resultData?.overview

            viewModel.content(idContent, resultData?.id ?: 0).observe(viewLifecycleOwner){
                if (it is Resource.Success){
                    txtTime.text = getString(R.string.title_minutes, viewModel.getRuntime(it.data, idContent))
                    genreAdapter?.submitList(it.data?.genres)
                }
            }

            viewModel.imageBackdrop(idContent, resultData?.id ?: 0).observe(viewLifecycleOwner){
                if (it is Resource.Success){
                    screenShotAdapter?.submitList(it.data?.backdrops)
                }
            }

            viewModel.isFavorite(resultData?.id ?: 0).observe(viewLifecycleOwner){
                if (it){
                    btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav))
                } else {
                    btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav_border))
                }
            }

            btnFavorite.setOnClickListener {
                resultData?.let { viewModel.setFavorite(it, idContent).observe(viewLifecycleOwner){ isFav ->
                    if (isFav){
                        btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav))
                    } else {
                        btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav_border))
                    }
                }}
            }
        }
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        genreAdapter = null
        screenShotAdapter = null

        binding?.apply {
            rvGenre.apply {
                layoutManager = null
                adapter = null
            }

            rvScreenshot.apply {
                layoutManager = null
                adapter = null
            }

            txtTitle.text = null
            txtRate.text = null
            txtRelease.text = null
            txtSynopsis.text = null
            txtTime.text = null
        }
    }


}