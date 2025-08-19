package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName


data class EpisodesResponse (

  @SerializedName("data" ) var data : Data? = Data()

)