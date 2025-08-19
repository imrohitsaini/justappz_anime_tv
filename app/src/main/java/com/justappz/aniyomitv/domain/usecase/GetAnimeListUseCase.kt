package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.Constants
import com.justappz.aniyomitv.domain.model.Anime
import com.justappz.aniyomitv.domain.model.AnimeRequest
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import jakarta.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AllAnimeRepo
) {
    suspend operator fun invoke(): List<Anime> {
        return repository.getAnimeList(AnimeRequest(
            query = Constants.POPULAR_ANIME_QUERY,
            variables = AnimeRequest.Variables(
                type = "anime",
                size = 100,
                dateRange = 7,
                page = 1
            )
        ))
    }
}