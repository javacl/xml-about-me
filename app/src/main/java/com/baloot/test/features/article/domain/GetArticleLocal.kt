package com.baloot.test.features.article.domain

import com.baloot.test.features.article.data.ArticleRepository
import javax.inject.Inject

class GetArticleLocal @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(articleId: Int) = articleRepository.getArticleLocal(articleId)
}
