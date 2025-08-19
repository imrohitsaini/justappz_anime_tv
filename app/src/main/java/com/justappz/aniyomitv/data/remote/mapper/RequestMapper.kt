package com.justappz.aniyomitv.data.remote.mapper

import com.justappz.aniyomitv.data.remote.model.AllAnimeRequest
import com.justappz.aniyomitv.domain.model.AnimeRequest

fun AnimeRequest.toData(): AllAnimeRequest {
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