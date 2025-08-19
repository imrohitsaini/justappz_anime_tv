package com.justappz.aniyomitv.data.remote.model

import com.google.gson.annotations.SerializedName


data class AnyCard(

    @SerializedName("_id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("englishName") var englishName: String? = null,
    @SerializedName("nativeName") var nativeName: String? = null,
    @SerializedName("slugTime") var slugTime: String? = null

)