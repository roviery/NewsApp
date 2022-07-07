package com.schoters.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("source")
    val source: SourceResponse,

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val image: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val content: String?
)

