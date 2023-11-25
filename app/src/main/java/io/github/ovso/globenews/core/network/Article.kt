package io.github.ovso.globenews.core.network

import kotlinx.serialization.Serializable


@Serializable
data class Article(
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)