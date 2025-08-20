package com.justappz.aniyomitv.data.remote.repo

import android.util.Log
import com.google.gson.Gson
import com.justappz.aniyomitv.data.remote.ApiService
import com.justappz.aniyomitv.data.remote.mapper.toData
import com.justappz.aniyomitv.data.remote.mapper.toDomain
import com.justappz.aniyomitv.domain.model.anime.AnimeDomain
import com.justappz.aniyomitv.domain.model.anime.AnimeRequestDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesRequestDomain
import com.justappz.aniyomitv.domain.model.streams.StreamsDomain
import com.justappz.aniyomitv.domain.model.streams.StreamsRequestDomain
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import javax.inject.Inject

class AllAnimeRepoImpl @Inject constructor(
    private val apiService: ApiService
) : AllAnimeRepo {

    override suspend fun getAnimeList(animeRequestDomain: AnimeRequestDomain): List<AnimeDomain> {
        val json = Gson().toJson(animeRequestDomain.toData())
        Log.d("AnimeRepo", "Request JSON: $json")
        val response = apiService.getPopularAnime(animeRequestDomain.toData())
        Log.d("AnimeRepo", "Response: ${Gson().toJson(response)}")
        return response.toDomain()
    }

    override suspend fun getEpisodesList(episodesRequestDomain: EpisodesRequestDomain): EpisodesDomain {
        val json = Gson().toJson(episodesRequestDomain.toData())
        Log.d("AnimeRepo", "Episodes Request JSON: $json")
        val response = apiService.getEpisodes(episodesRequestDomain.toData())
        Log.d("AnimeRepo", "Episodes Response: ${Gson().toJson(response)}")
        var episodes = response.toDomain()
        if (episodes == null) {
            Log.e("AnimeRepo", "Episodes response is null")
            episodes = EpisodesDomain(sub = emptyList(), dub = emptyList(), raw = emptyList())
        }
        return episodes
    }

    override suspend fun getStreamsList(streamsRequestDomain: StreamsRequestDomain): List<StreamsDomain> {
        val json = Gson().toJson(streamsRequestDomain.toData())
        Log.d("AnimeRepo", "Streams Request JSON: $json")
        val response = apiService.getStreams(streamsRequestDomain.toData())
        Log.d("AnimeRepo", "Streams Response: ${Gson().toJson(response)}")
        return response.toDomain()
    }
}