package com.justappz.aniyomitv.data.remote.model.anime

import com.google.gson.annotations.SerializedName


data class Recommendations (

  @SerializedName("anyCard" ) var anyCard : AnyCard? = AnyCard()

)