package com.justappz.aniyomitv.data.remote.model.anime

data class AllAnimeRequest(
    val query: String,
    val variables: Variables
) {
    data class Variables(
        val type: String,
        val size: Int,
        val dateRange: Int,
        val page: Int
    )
}