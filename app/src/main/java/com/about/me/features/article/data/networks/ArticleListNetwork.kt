package com.about.me.features.article.data.networks

import com.about.me.features.article.data.entities.ArticleEntity

data class ArticleListNetwork(
    val articles: List<ArticleEntity> = listOf()
)
