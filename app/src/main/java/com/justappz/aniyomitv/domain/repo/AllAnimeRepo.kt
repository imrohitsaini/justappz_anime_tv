package com.justappz.aniyomitv.domain.repo

import com.justappz.aniyomitv.domain.model.AnimeDomain
import com.justappz.aniyomitv.domain.model.AnimeRequestDomain
import com.justappz.aniyomitv.domain.model.EpisodesDomain
import com.justappz.aniyomitv.domain.model.EpisodesRequestDomain

interface AllAnimeRepo {
    suspend fun getAnimeList(animeRequestDomain: AnimeRequestDomain): List<AnimeDomain>

    suspend fun getEpisodesList(episodesRequestDomain: EpisodesRequestDomain): EpisodesDomain
}