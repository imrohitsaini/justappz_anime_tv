package com.justappz.aniyomitv.data.remote.model.episodes

import com.google.gson.annotations.SerializedName


data class Show(

    @SerializedName("_id") var id: String? = null,
    @SerializedName("availableEpisodesDetail") var availableEpisodesDetail: AvailableEpisodesDetail? = AvailableEpisodesDetail(),
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("season") var season: Season? = Season(),
    @SerializedName("score") var score: Double? = null,
    @SerializedName("genres") var genres: ArrayList<String> = arrayListOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("studios") var studios: ArrayList<String> = arrayListOf()

)