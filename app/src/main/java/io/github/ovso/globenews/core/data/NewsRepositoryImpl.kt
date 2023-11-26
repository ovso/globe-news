package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.ArticleResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataSource: TopHeadlinesDataSource,
) : NewsRepository {
    override suspend fun getTopHeadlines(): List<ArticleResponse> = dataSource.getArticles()
}