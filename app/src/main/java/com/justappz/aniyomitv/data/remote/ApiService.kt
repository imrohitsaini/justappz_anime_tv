package com.justappz.aniyomitv.data.remote

import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeRequest
import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeResponse
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesRequest
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api")
    suspend fun getPopularAnime(@Body request: AllAnimeRequest): AllAnimeResponse

    @Headers("Content-Type: application/json")
    @POST("api")
    suspend fun getEpisodes(@Body request: EpisodesRequest): EpisodesResponse
}