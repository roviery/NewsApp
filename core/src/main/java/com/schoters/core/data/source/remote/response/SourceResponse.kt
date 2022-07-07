package com.schoters.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)
