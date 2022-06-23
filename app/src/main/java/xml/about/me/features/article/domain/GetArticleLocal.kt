package xml.about.me.features.article.domain

import xml.about.me.features.article.data.ArticleRepository
import javax.inject.Inject

class GetArticleLocal @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(articleId: Int) = articleRepository.getArticleLocal(articleId)
}
