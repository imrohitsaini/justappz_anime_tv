package com.justappz.aniyomitv.data.remote

import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeRequest
import com.justappz.aniyomitv.data.remote.model.anime.AllAnimeResponse
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesRequest
import com.justappz.aniyomitv.data.remote.model.episodes.EpisodesResponse
import com.justappz.aniyomitv.data.remote.model.streams.StreamsRequest
import com.justappz.aniyomitv.data.remote.model.streams.StreamsResponse
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

    @Headers("Content-Type: application/json")
    @POST("api")
    suspend fun getStreams(@Body request: StreamsRequest): StreamsResponse
}