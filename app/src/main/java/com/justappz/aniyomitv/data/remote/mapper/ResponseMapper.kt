package com.justappz.aniyomitv.data.remote.mapper

import com.justappz.aniyomitv.data.remote.model.AllAnimeResponse
import com.justappz.aniyomitv.domain.model.Anime

fun AllAnimeResponse.toDomain(): List<Anime> {
    val data = this.data ?: return emptyList()
    if (data.queryPopular == null) return emptyList()
    return data.queryPopular!!.recommendations
        .mapNotNull { it.anyCard }
        .map { card ->
            Anime(
                id = card.id,
                name = card.name,
                thumbnail = card.thumbnail,
                englishName = card.englishName,
                nativeName = card.nativeName
            )
        }
}