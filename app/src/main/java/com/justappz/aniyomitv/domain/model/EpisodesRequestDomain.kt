package com.justappz.aniyomitv.domain.model

import com.google.gson.annotations.SerializedName

data class EpisodesRequestDomain(
    val query: String,
    val variables: Variables
) {
    data class Variables(
        @SerializedName("_id" )val id : String,
    )
}