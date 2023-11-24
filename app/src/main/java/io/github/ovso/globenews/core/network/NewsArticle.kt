package io.github.ovso.globenews.core.network

data class NewsArticle(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)