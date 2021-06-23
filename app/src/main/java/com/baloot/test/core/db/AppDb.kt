package com.baloot.test.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baloot.test.features.article.data.ArticleDao
import com.baloot.test.features.article.data.entities.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
