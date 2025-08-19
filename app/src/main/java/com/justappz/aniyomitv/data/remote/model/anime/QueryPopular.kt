package com.justappz.aniyomitv.data.remote.model.anime

import com.google.gson.annotations.SerializedName


data class QueryPopular (

  @SerializedName("total"           ) var total           : Int?                       = null,
  @SerializedName("recommendations" ) var recommendations : ArrayList<Recommendations> = arrayListOf()

)