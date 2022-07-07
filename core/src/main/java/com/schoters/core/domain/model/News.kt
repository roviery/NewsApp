package com.schoters.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int,
    val name: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val image: String?,
    val publishedAt: String?,
    val content: String?
) : Parcelable