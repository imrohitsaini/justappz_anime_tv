package com.justappz.aniyomitv.domain.model.episodes

data class EpisodesDomain(
    var thumbnail: String? = null,
    var description: String? = null,
    var type: String? = null,
    val sub: List<String>,
    val dub: List<String>,
    val raw: List<String>,
    var score: Double? = null,
    var genres: ArrayList<String> = arrayListOf(),
    var status: String? = null,
    var studios: ArrayList<String> = arrayListOf(),
    var season: SeasonDomain? = SeasonDomain(),
)