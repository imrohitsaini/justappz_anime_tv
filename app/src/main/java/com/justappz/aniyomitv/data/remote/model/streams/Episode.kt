package com.justappz.aniyomitv.data.remote.model.streams

import com.google.gson.annotations.SerializedName


data class Episode(

    @SerializedName("sourceUrls") var sourceUrls: ArrayList<SourceUrls> = arrayListOf()

)