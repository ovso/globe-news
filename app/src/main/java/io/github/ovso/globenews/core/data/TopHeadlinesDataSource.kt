package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.ArticleResponse

interface TopHeadlinesDataSource {
    suspend fun getArticles(): List<ArticleResponse>
}
