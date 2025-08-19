package com.justappz.aniyomitv.data.remote

import com.justappz.aniyomitv.data.remote.model.AllAnimeRequest
import com.justappz.aniyomitv.data.remote.model.AllAnimeResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api")
    suspend fun getPopularAnime(@Body request: AllAnimeRequest): AllAnimeResponse
}