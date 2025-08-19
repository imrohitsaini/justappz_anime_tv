package com.justappz.aniyomitv.domain.model

data class AnimeRequest(
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