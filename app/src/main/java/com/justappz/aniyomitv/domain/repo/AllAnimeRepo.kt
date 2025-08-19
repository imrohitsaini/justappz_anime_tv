package com.justappz.aniyomitv.domain.repo

import com.justappz.aniyomitv.domain.model.anime.AnimeDomain
import com.justappz.aniyomitv.domain.model.anime.AnimeRequestDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesRequestDomain

interface AllAnimeRepo {
    suspend fun getAnimeList(animeRequestDomain: AnimeRequestDomain): List<AnimeDomain>

    suspend fun getEpisodesList(episodesRequestDomain: EpisodesRequestDomain): EpisodesDomain
}