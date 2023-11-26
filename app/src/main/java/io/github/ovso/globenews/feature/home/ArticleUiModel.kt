package io.github.ovso.globenews.feature.home

data class ArticleUiModel(
    val publishedAt: String,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val viewed: Int = 0,
)