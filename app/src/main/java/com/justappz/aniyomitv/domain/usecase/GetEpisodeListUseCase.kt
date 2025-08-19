package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.constants.Constants
import com.justappz.aniyomitv.domain.model.episodes.EpisodesDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesRequestDomain
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