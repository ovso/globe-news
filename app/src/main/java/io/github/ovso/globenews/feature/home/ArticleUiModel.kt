package io.github.ovso.globenews.feature.home

data class ArticleUiModel(
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val viewed: Boolean = false,
)