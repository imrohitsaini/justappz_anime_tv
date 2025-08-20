package com.justappz.aniyomitv.domain.usecase

import com.justappz.aniyomitv.constants.RequestQueries
import com.justappz.aniyomitv.domain.model.episodes.EpisodesDomain
import com.justappz.aniyomitv.domain.model.episodes.EpisodesRequestDomain
import com.justappz.aniyomitv.domain.model.streams.StreamsDomain
import com.justappz.aniyomitv.domain.model.streams.StreamsRequestDomain
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import jakarta.inject.Inject

class GetStreamsListUseCase @Inject constructor(
    private val repository: AllAnimeRepo
) {
    suspend operator fun invoke(showId: String, episodeString: String): List<StreamsDomain> {
        return repository.getStreamsList(
            StreamsRequestDomain(
                query = RequestQueries.EPISODE_SOURCE_QUERY,
                variables = StreamsRequestDomain.Variables(
                    id = showId,
                    translationType = "sub",
                    episodeString = episodeString
            )
        ))
    }
}