package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName


data class AvailableEpisodesDetail (

  @SerializedName("sub" ) var sub : ArrayList<String> = arrayListOf(),
  @SerializedName("dub" ) var dub : ArrayList<String> = arrayListOf(),
  @SerializedName("raw" ) var raw : ArrayList<String> = arrayListOf()

)