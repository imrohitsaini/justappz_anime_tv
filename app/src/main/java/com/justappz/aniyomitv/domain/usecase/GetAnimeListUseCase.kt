package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.constants.Constants
import com.justappz.aniyomitv.domain.model.anime.AnimeDomain
import com.justappz.aniyomitv.domain.model.anime.AnimeRequestDomain
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
                size = 20,
                dateRange = 7,
                page = 1
            )
        ))
    }
}