package com.justappz.aniyomitv.domain.repo

import com.justappz.aniyomitv.domain.model.Anime
import com.justappz.aniyomitv.domain.model.AnimeRequest

interface AllAnimeRepo {
    suspend fun getAnimeList(animeRequest: AnimeRequest): List<Anime>
}