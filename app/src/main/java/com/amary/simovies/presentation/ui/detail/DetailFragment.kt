package com.amary.simovies.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amary.simovies.BuildConfig
import com.amary.simovies.R
import com.amary.simovies.base.BaseFragment
import com.amary.simovies.constant.KeyValue
import com.amary.simovies.core.Resource
import com.amary.simovies.databinding.FragmentDetailBinding
import com.amary.simovies.domain.model.Result
import com.amary.simovies.presentation.adapter.GenreAdapter
import com.amary.simovies.presentation.adapter.ScreenShotAdapter
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModel()
    private val result: Result? by lazy { arguments?.getSerializable(KeyValue.BUNDLE_DETAIL) as Result }
    private val idContent: Int by lazy { arguments?.getInt(KeyValue.BUNDLE_CONTENT) ?: 1 }
    private val genreAdapter: GenreAdapter by lazy { GenreAdapter() }
    private val screenShotAdapter: ScreenShotAdapter by lazy { ScreenShotAdapter() }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            rvGenre.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvGenre.adapter = genreAdapter

            rvScreenshot.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvScreenshot.adapter = screenShotAdapter

            Glide.with(requireContext())
                .load(BuildConfig.BASE_IMAGE + result?.backdropPath)
                .into(imgPoster)

            txtTitle.text = result?.title
            txtRate.text = result?.voteAverage.toString()
            txtRelease.text = viewModel.convertDateFormat(result?.releaseDate.toString())
            txtSynopsis.text = result?.overview

            viewModel.content(idContent, result?.id ?: 0).observe(viewLifecycleOwner){
                if (it is Resource.Success){
                    txtTime.text = getString(R.string.title_minutes, viewModel.getRuntime(it.data, idContent))
                    genreAdapter.submitList(it.data?.genres)
                }
            }

            viewModel.imageBackdrop(idContent, result?.id ?: 0).observe(viewLifecycleOwner){
                if (it is Resource.Success){
                    screenShotAdapter.submitList(it.data?.backdrops)
                }
            }

            viewModel.isFavorite(result?.id ?: 0).observe(viewLifecycleOwner){
                if (it){
                    btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav))
                } else {
                    btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav_border))
                }
            }

            btnFavorite.setOnClickListener {
                result?.let { viewModel.setFavorite(it, idContent).observe(viewLifecycleOwner){ isFav ->
                    if (isFav){
                        btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav))
                    } else {
                        btnFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_fav_border))
                    }
                }}
            }
        }
    }






}