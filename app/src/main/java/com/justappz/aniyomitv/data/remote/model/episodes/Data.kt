package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("show" ) var show : Show? = Show()

)