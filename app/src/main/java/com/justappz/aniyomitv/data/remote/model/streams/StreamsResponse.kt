package com.justappz.aniyomitv.data.remote.model.streams

import com.google.gson.annotations.SerializedName


data class StreamsResponse(

    @SerializedName("data") var data: Data? = Data()

)