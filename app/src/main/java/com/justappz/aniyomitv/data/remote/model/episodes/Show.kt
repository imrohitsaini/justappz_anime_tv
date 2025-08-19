package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName


data class Show (

  @SerializedName("_id"                     ) var id                      : String?                  = null,
  @SerializedName("availableEpisodesDetail" ) var availableEpisodesDetail : AvailableEpisodesDetail? = AvailableEpisodesDetail()

)