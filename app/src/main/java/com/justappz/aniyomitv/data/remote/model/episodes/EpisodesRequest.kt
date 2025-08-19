package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName

data class EpisodesRequest(
    val query: String,
    val variables: Variables
) {
    data class Variables(
        @SerializedName("_id" )val id : String,
    )
}