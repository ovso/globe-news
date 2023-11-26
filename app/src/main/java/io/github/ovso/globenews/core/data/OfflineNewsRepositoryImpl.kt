package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineNewsRepositoryImpl @Inject constructor(
    private val dataSource: OfflineTopHeadlinesDataSource,
) : OfflineNewsRepository {
    override suspend fun getArticles(): Flow<List<ArticleEntity>> =
        dataSource.getArticles()

    override suspend fun insertAll(articles: List<ArticleEntity>) = dataSource.insertAll(articles)
    override suspend fun upsert(article: ArticleEntity) = dataSource.upsert(article)

    override suspend fun clearData() = dataSource.clearData()


}