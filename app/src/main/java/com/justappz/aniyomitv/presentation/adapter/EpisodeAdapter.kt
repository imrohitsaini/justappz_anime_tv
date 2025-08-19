package com.justappz.aniyomitv.presentation.adapter

import com.justappz.aniyomitv.base.BaseRecyclerViewAdapter
import com.justappz.aniyomitv.databinding.ItemEpisodesBinding

class EpisodeAdapter(
    episodes: List<String>
) : BaseRecyclerViewAdapter<String, ItemEpisodesBinding>(
    items = episodes,
    bindingInflater = ItemEpisodesBinding::inflate,
    bind = { episodeNumber, _ ->
        tvEpisodeNumber.text = episodeNumber
    }
)