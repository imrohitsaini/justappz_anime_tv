package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.Constants
import com.justappz.aniyomitv.domain.model.AnimeDomain
import com.justappz.aniyomitv.domain.model.AnimeRequestDomain
import com.justappz.aniyomitv.domain.model.EpisodesDomain
import com.justappz.aniyomitv.domain.model.EpisodesRequestDomain
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import jakarta.inject.Inject

class GetEpisodeListUseCase @Inject constructor(
    private val repository: AllAnimeRepo
) {
    suspend operator fun invoke(queryId: String): EpisodesDomain {
        return repository.getEpisodesList(EpisodesRequestDomain(
            query = Constants.EPISODE_QUERY,
            variables = EpisodesRequestDomain.Variables(
                id = queryId
            )
        ))
    }
}