package com.justappz.aniyomitv.data.remote.model

import com.google.gson.annotations.SerializedName


data class Recommendations (

  @SerializedName("anyCard" ) var anyCard : AnyCard? = AnyCard()

)