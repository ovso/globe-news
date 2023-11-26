package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface OfflineTopHeadlinesDataSource {
    fun getArticles(): Flow<List<ArticleEntity>>

    suspend fun insert(article: ArticleEntity)

    suspend fun insertAll(articles: List<ArticleEntity>)
    suspend fun clearData()

}