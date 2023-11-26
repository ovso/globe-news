package io.github.ovso.globenews.core.network

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)