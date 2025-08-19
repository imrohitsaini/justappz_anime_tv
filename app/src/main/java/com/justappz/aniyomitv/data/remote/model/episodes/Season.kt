package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName

data class Season(

    @SerializedName("quarter") var quarter: String? = null,
    @SerializedName("year") var year: Int? = null

)