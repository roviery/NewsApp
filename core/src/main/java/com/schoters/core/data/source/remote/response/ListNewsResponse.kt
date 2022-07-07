package com.schoters.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListNewsResponse(

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val listNews: List<NewsResponse>

)
