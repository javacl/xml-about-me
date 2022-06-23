package xml.about.me.features.article.data

import xml.about.me.features.article.data.networks.ArticleListNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ArticleService {

    @GET
    suspend fun getArticleList(
        @Url url: String,
        @Query(value = "apiKey", encoded = true) apiKey: String,
        @Query(value = "domains", encoded = true) domains: String,
        @Query(value = "page", encoded = true) page: Int
    ): Response<ArticleListNetwork>
}
