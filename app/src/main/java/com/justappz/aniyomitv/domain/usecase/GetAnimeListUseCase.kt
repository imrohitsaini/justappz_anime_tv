package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.Constants
import com.justappz.aniyomitv.domain.model.AnimeDomain
import com.justappz.aniyomitv.domain.model.AnimeRequestDomain
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import jakarta.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AllAnimeRepo
) {
    suspend operator fun invoke(): List<AnimeDomain> {
        return repository.getAnimeList(AnimeRequestDomain(
            query = Constants.POPULAR_ANIME_QUERY,
            variables = AnimeRequestDomain.Variables(
                type = "anime",
                size = 100,
                dateRange = 7,
                page = 1
            )
        ))
    }
}