package com.justappz.aniyomitv.data.remote.mapper

import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeResponse
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesResponse
import com.justappz.aniyomitv.domain.model.AnimeDomain
import com.justappz.aniyomitv.domain.model.EpisodesDomain

fun AllAnimeResponse.toDomain(): List<AnimeDomain> {
    val data = this.data ?: return emptyList()
    if (data.queryPopular == null) return emptyList()
    return data.queryPopular!!.recommendations
        .mapNotNull { it.anyCard }
        .map { card ->
            AnimeDomain(
                id = card.id,
                name = card.name,
                thumbnail = card.thumbnail,
                englishName = card.englishName,
                nativeName = card.nativeName
            )
        }
}

fun EpisodesResponse.toDomain(): EpisodesDomain? {
    val data = this.data ?: return null
    val show = data.show ?: return null
    val episodesDetail = show.availableEpisodesDetail ?: return null

    val sub = episodesDetail.sub
    val dub = episodesDetail.dub
    val raw = episodesDetail.raw

    return EpisodesDomain (
        sub = sub,
        dub = dub,
        raw = raw,
    )
}