package com.justappz.aniyomitv.presentation.adapter

import com.bumptech.glide.Glide
import com.justappz.aniyomitv.base.BaseRecyclerViewAdapter
import com.justappz.aniyomitv.databinding.ItemAnimeBinding
import com.justappz.aniyomitv.domain.model.Anime

class AnimeAdapter(
    animes: List<Anime>
) : BaseRecyclerViewAdapter<Anime, ItemAnimeBinding>(
    items = animes,
    bindingInflater = ItemAnimeBinding::inflate,
    bind = { anime, _ ->

        tvAnimeName.text = anime.englishName?.trim()

        // Load image using Glide
        Glide.with(root.context)
            .load(anime.thumbnail) // URL or drawable
            .into(ivAnimeThumbnail)   // your ImageView from binding
    }
)