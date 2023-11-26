package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.database.dao.ArticleDao
import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val dao: ArticleDao
): NewsLocalDataSource {
    override suspend fun getArticles(): Flow<List<ArticleEntity>> = dao.getArticles()

    override suspend fun insert(article: ArticleEntity) = dao.insert(article)

    override suspend fun insertAll(articles: List<ArticleEntity>) = dao.insertAll(articles)

    override suspend fun clearData() = dao.clearData()
}