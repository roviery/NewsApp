package com.schoters.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SavedNews")
data class NewsEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val image: String?,
    val publishedAt: String?,
    val content: String?
)