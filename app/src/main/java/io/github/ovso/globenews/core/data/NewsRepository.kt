package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.ArticleResponse

interface NewsRepository {
    suspend fun getTopHeadlines(): List<ArticleResponse>
}