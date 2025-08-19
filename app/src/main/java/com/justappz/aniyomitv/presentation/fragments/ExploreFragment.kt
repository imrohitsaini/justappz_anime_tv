package com.justappz.aniyomitv.presentation.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.justappz.aniyomitv.databinding.FragmentExploreBinding
import com.justappz.aniyomitv.domain.model.Anime
import com.justappz.aniyomitv.domain.model.AnimeUiState
import com.justappz.aniyomitv.presentation.adapter.AnimeAdapter
import com.justappz.aniyomitv.presentation.viewmodel.AnimeViewModel
import com.justappz.aniyomitv.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    //region variables
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: Activity
    private val viewModel: AnimeViewModel by viewModels()
    //endregion

    //region onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }
    //endregion

    //region onDestroyView
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    //region onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity = requireActivity()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is AnimeUiState.Loading -> {
                            // show loading indicator
                            binding.progessbarLoading.visibility = View.VISIBLE
                        }

                        is AnimeUiState.Success -> {
                            binding.progessbarLoading.visibility = View.GONE
                            val animeList = state.data
                            setAnimeList(animeList)
                        }

                        is AnimeUiState.Error -> {
                            binding.progessbarLoading.visibility = View.GONE
                            ToastUtils.showToast(state.message, activity)
                        }
                    }
                }
            }
        }
        viewModel.fetchAnimeList()
    }
    //endregion

    //region setAnimeList
    private fun setAnimeList(animeList: List<Anime>) {
        val spanCount = if (resources.configuration.smallestScreenWidthDp >= 600) 5 else 3

        val adapter = AnimeAdapter(animeList)

        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding.rvAnime.layoutManager = layoutManager
        binding.rvAnime.adapter = adapter
        binding.rvAnime.isVisible = true

    }
    //endregion

}