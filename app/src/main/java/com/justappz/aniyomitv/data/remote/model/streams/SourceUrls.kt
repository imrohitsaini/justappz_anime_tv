package com.justappz.aniyomitv.data.remote.model.streams

import com.google.gson.annotations.SerializedName


data class SourceUrls(

    @SerializedName("sourceUrl") var sourceUrl: String? = null,
    @SerializedName("priority") var priority: Double? = null,
    @SerializedName("sourceName") var sourceName: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("className") var className: String? = null,
    @SerializedName("streamerId") var streamerId: String? = null

)