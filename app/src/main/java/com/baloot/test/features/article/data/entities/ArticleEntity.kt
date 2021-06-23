package com.baloot.test.features.article.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)
