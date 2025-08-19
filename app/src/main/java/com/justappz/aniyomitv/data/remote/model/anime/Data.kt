package com.justappz.aniyomitv.data.remote.model.anime

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("queryPopular" ) var queryPopular : QueryPopular? = QueryPopular()

)