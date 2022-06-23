package xml.about.me.features.article.data.networks

import xml.about.me.features.article.data.entities.ArticleEntity

data class ArticleListNetwork(
    val articles: List<ArticleEntity> = listOf()
)
