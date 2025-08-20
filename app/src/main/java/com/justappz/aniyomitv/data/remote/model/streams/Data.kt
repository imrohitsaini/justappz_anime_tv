package com.justappz.aniyomitv.data.remote.model.streams

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("episode") var episode: Episode? = Episode()

)