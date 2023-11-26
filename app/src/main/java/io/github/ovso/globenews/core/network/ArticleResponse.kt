package io.github.ovso.globenews.core.network

import kotlinx.serialization.Serializable


@Serializable
data class ArticleResponse(
    val publishedAt: String,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
)