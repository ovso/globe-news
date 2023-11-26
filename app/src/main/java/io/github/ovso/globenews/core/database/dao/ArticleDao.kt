package io.github.ovso.globenews.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)
    @Query("DELETE FROM article_table")
    suspend fun clearData()
}