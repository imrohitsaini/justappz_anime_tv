package com.justappz.aniyomitv.data.remote.model.anime

import com.google.gson.annotations.SerializedName


data class AllAnimeResponse (

  @SerializedName("data" ) var data : Data? = Data()

)