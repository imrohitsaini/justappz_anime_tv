package com.justappz.aniyomitv.presentation.adapter

import com.bumptech.glide.Glide
import com.justappz.aniyomitv.R
import com.justappz.aniyomitv.base.BaseRecyclerViewAdapter
import com.justappz.aniyomitv.databinding.ItemAnimeBinding
import com.justappz.aniyomitv.domain.model.anime.AnimeDomain

class AnimeAdapter(
    animeDomains: List<AnimeDomain>
) : BaseRecyclerViewAdapter<AnimeDomain, ItemAnimeBinding>(
    items = animeDomains,
    bindingInflater = ItemAnimeBinding::inflate,
    bind = { anime, _ ->

        tvAnimeName.text = anime.englishName?.trim()

        var thumbnailUrl = anime.thumbnail

        if (thumbnailUrl?.startsWith("mcovers") == true) {
            thumbnailUrl = "https://wp.youtube-anime.com/aln.youtube-anime.com/$thumbnailUrl?w=150"
        }

        // Load image using Glide
        Glide.with(root.context)
            .load(thumbnailUrl) // URL or drawable
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher) // Error image
            .into(ivAnimeThumbnail)   // your ImageView from binding
    }
)