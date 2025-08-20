package com.justappz.aniyomitv.domain.model.streams

import com.google.gson.annotations.SerializedName

data class StreamsDomain(

    @SerializedName("sourceUrl") var sourceUrl: String? = null,
    @SerializedName("priority") var priority: Double? = null,
    @SerializedName("sourceName") var sourceName: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("className") var className: String? = null,
    @SerializedName("streamerId") var streamerId: String? = null

)