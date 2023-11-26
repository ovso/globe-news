package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface OfflineNewsRepository {

    suspend fun getArticles(): Flow<List<ArticleEntity>>

    suspend fun insertAll(articles: List<ArticleEntity>)

    suspend fun upsert(article: ArticleEntity)
    suspend fun clearData()

}