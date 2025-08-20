package com.justappz.aniyomitv.data.remote.model.streams

import com.google.gson.annotations.SerializedName

data class StreamsRequest(
    val query: String,
    val variables: Variables
) {
    data class Variables(
        @SerializedName("showId") val id: String,
        @SerializedName("translationType") val translationType: String,
        @SerializedName("episodeString") val episodeString: String,
    )
}