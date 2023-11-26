package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.database.dao.ArticleDao
import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineTopHeadlinesDataSourceImpl @Inject constructor(
    private val dao: ArticleDao
): OfflineTopHeadlinesDataSource {
    override fun getArticles(): Flow<List<ArticleEntity>> = dao.getArticles()

    override suspend fun insert(article: ArticleEntity) = dao.insert(article)

    override suspend fun insertAll(articles: List<ArticleEntity>) = dao.insertAll(articles)

    override suspend fun clearData() = dao.clearData()
}