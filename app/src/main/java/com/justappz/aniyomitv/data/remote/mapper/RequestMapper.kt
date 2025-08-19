package com.justappz.aniyomitv.data.remote.mapper

import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeRequest
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesRequest
import com.justappz.aniyomitv.domain.model.AnimeRequestDomain
import com.justappz.aniyomitv.domain.model.EpisodesRequestDomain

fun AnimeRequestDomain.toData(): AllAnimeRequest {
    return AllAnimeRequest(
        query = this.query,
        variables = AllAnimeRequest.Variables(
            type = this.variables.type,
            size = this.variables.size,
            dateRange = this.variables.dateRange,
            page = this.variables.page
        )
    )
}

fun EpisodesRequestDomain.toData(): EpisodesRequest {
    return EpisodesRequest(
        query = this.query,
        variables = EpisodesRequest.Variables(
            id = this.variables.id
        )
    )
}