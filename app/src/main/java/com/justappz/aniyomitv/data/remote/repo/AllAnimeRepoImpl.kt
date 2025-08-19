package com.justappz.aniyomitv.data.remote.repo

import android.util.Log
import com.google.gson.Gson
import com.justappz.aniyomitv.data.remote.ApiService
import com.justappz.aniyomitv.data.remote.mapper.toData
import com.justappz.aniyomitv.data.remote.mapper.toDomain
import com.justappz.aniyomitv.domain.model.Anime
import com.justappz.aniyomitv.domain.model.AnimeRequest
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import org.json.JSONArray
import javax.inject.Inject

class AllAnimeRepoImpl @Inject constructor(
    private val apiService: ApiService
) : AllAnimeRepo {

    override suspend fun getAnimeList(animeRequest: AnimeRequest): List<Anime> {
        val json = Gson().toJson(animeRequest.toData())
        Log.d("AnimeRepo", "Request JSON: $json")
        val response = apiService.getPopularAnime(animeRequest.toData())
        Log.d("AnimeRepo", "Response: ${Gson().toJson(response)}")
        return response.toDomain()
    }

}