package xml.about.me.features.article.data

import xml.about.me.core.api.BaseRemoteDataSource
import xml.about.me.core.util.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRemoteDataSource @Inject constructor(
    private val articleService: ArticleService
) : BaseRemoteDataSource() {

    suspend fun getArticleList(page: Int) = safeApiCall(
        call = { requestGetArticleList(page = page) },
        errorMessage = "Error get article list"
    )

    private suspend fun requestGetArticleList(page: Int) = checkApiResult(
        articleService.getArticleList(
            url = urls.GET_ARTICLE_LIST_API_URL,
            apiKey = "10307e6f365542929dd5d0a9ed2395bb",
            domains = "wsj.com",
            page = page
        )
    )
}
