package io.github.ovso.globenews.core.network

import kotlinx.serialization.Serializable

@Serializable
data class NewsArticle(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)