package com.justappz.aniyomitv.presentation.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.justappz.aniyomitv.constants.IntentKeys
import com.justappz.aniyomitv.databinding.ActivityEpisodesBinding
import com.justappz.aniyomitv.domain.model.episodes.EpisodesDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesUiState
import com.justappz.aniyomitv.domain.model.streams.StreamsUiState
import com.justappz.aniyomitv.presentation.adapter.EpisodeAdapter
import com.justappz.aniyomitv.presentation.viewmodel.AnimeViewModel
import com.justappz.aniyomitv.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesActivity : AppCompatActivity() {

    //region variables
    private lateinit var binding: ActivityEpisodesBinding
    private lateinit var activity: Activity
    private val viewModel: AnimeViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter
    private var animeId: String? = null
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = this
        binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animeId = intent.getStringExtra(IntentKeys.ANIME_ID)


        init()
    }
    //endregion

    //region init
    private fun init() {
        val spanCount = if (resources.configuration.smallestScreenWidthDp >= 600) 7 else 5
        val layoutManager = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)

        episodeAdapter = EpisodeAdapter(emptyList()).apply {
            onItemClick = { episode, _ ->
                viewModel.fetchStreamsList(animeId!!, episode)
            }
        }

        binding.rvEpisodes.layoutManager = layoutManager
        binding.rvEpisodes.adapter = episodeAdapter

        collectEpisodesList()
        collectStreamList()
        animeId?.let {
            viewModel.fetchEpisodesList(it)
        }
    }
    //endregion

    //region collectEpisodesList
    private fun collectEpisodesList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.episodesState.collect { state ->
                    when (state) {
                        is EpisodesUiState.Loading -> {
                            binding.progressbarLoading.visibility = View.VISIBLE
                        }

                        is EpisodesUiState.Success -> {
                            binding.progressbarLoading.visibility = View.GONE
                            val episodeList = state.data
                            setEpisodes(episodeList)
                        }

                        is EpisodesUiState.Error -> {
                            binding.progressbarLoading.visibility = View.GONE
                            ToastUtils.showToast(state.message, activity)
                        }
                    }
                }
            }
        }
    }
    //endregion

    //region collectStreamList
    private fun collectStreamList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.streamsState.collect { state ->
                    when (state) {
                        is StreamsUiState.Loading -> {
                            binding.progressbarLoading.visibility = View.VISIBLE
                        }

                        is StreamsUiState.Success -> {
                            binding.progressbarLoading.visibility = View.GONE
                            val streams = state.data
                        }

                        is StreamsUiState.Error -> {
                            binding.progressbarLoading.visibility = View.GONE
                            ToastUtils.showToast(state.message, activity)
                        }
                    }
                }
            }
        }
    }
    //endregion

    //region setEpisodes
    private fun setEpisodes(episodes: EpisodesDomain) {
        episodeAdapter.updateList(episodes.sub)
        binding.rvEpisodes.isVisible = true
    }
    //endregion
}