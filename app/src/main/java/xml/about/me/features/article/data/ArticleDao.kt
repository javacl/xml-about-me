package xml.about.me.features.article.data

import androidx.room.*
import xml.about.me.features.article.data.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Transaction
    @Query("SELECT * FROM ArticleEntity")
    fun getArticleList(): Flow<List<ArticleEntity>?>

    @Transaction
    @Query("SELECT * FROM ArticleEntity WHERE id = :articleId")
    fun getArticle(articleId: Int): Flow<ArticleEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleList: List<ArticleEntity>?)

    @Query("DELETE FROM ArticleEntity")
    suspend fun deleteArticleList()
}
