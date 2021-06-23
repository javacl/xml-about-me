package com.baloot.test.features.article.data.networks

import com.baloot.test.features.article.data.entities.ArticleEntity
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ArticleListNetwork(
    val articles: List<ArticleEntity> = listOf()
)
