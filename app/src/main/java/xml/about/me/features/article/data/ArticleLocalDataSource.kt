package xml.about.me.features.article.data

import androidx.room.withTransaction
import xml.about.me.core.db.AppDb
import xml.about.me.features.article.data.entities.ArticleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleLocalDataSource @Inject constructor(
    private val appDb: AppDb,
    private val articleDao: ArticleDao
) {
    fun getArticleList() = articleDao.getArticleList()

    fun getArticle(articleId: Int) = articleDao.getArticle(articleId)

    suspend fun insertArticleList(
        page: Int,
        articleList: List<ArticleEntity>
    ) {
        appDb.withTransaction {
            if (page == 1)
                articleDao.deleteArticleList()
            articleDao.insertArticleList(articleList)
        }
    }
}
