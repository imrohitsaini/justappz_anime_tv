package com.justappz.aniyomitv.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.justappz.aniyomitv.constants.IntentKeys
import com.justappz.aniyomitv.databinding.FragmentExploreBinding
import com.justappz.aniyomitv.domain.model.anime.AnimeDomain
import com.justappz.aniyomitv.domain.model.anime.AnimeUiState
import com.justappz.aniyomitv.presentation.activities.EpisodesActivity
import com.justappz.aniyomitv.presentation.adapter.AnimeAdapter
import com.justappz.aniyomitv.presentation.viewmodel.AnimeViewModel
import com.justappz.aniyomitv.utils.DisplayUtils
import com.justappz.aniyomitv.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    //region variables
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var parentActivity: Activity
    private val viewModel: AnimeViewModel by viewModels()
    private lateinit var animeAdapter: AnimeAdapter
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
        parentActivity = requireActivity()
        init()
    }
    //endregion

    //region init
    private fun init() {
        val spanCount = DisplayUtils.calculateSpanCount(parentActivity, 150, 2)
        val layoutManager = GridLayoutManager(parentActivity, spanCount, GridLayoutManager.VERTICAL, false)

        animeAdapter = AnimeAdapter(emptyList()).apply {
            onItemClick = { anime, _ ->
                startActivity(Intent(parentActivity, EpisodesActivity::class.java).apply {
                    putExtra(IntentKeys.ANIME_ID, anime.id)
                })
            }
        }

        binding.rvAnime.layoutManager = layoutManager
        binding.rvAnime.adapter = animeAdapter

        collectAnimeList()
        viewModel.fetchAnimeList()
    }

    //region collectAnimeList
    private fun collectAnimeList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is AnimeUiState.Loading -> {
                            // show loading indicator
                            binding.progressbarLoading.visibility = View.VISIBLE
                        }

                        is AnimeUiState.Success -> {
                            binding.progressbarLoading.visibility = View.GONE
                            val animeList = state.data
                            setAnimeList(animeList)
                        }

                        is AnimeUiState.Error -> {
                            binding.progressbarLoading.visibility = View.GONE
                            ToastUtils.showToast(state.message, parentActivity)
                        }
                    }
                }
            }
        }
    }
    //endregion

    //region setAnimeList
    private fun setAnimeList(animeDomainList: List<AnimeDomain>) {
        animeAdapter.updateList(animeDomainList) // Or submitList if using ListAdapter
        binding.rvAnime.isVisible = true
    }
    //endregion


}