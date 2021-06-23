package com.baloot.test.features.article.data

import androidx.room.withTransaction
import com.baloot.test.core.db.AppDb
import com.baloot.test.features.article.data.entities.ArticleEntity
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
