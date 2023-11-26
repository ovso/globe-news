package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.ArticleResponse
import io.github.ovso.globenews.core.network.TopHeadlinesApi
import javax.inject.Inject

class TopHeadlinesDataSourceImpl @Inject constructor(
    private val api: TopHeadlinesApi
): TopHeadlinesDataSource {
    override suspend fun getArticles(): List<ArticleResponse> = api.getTopHeadLines().articles
}