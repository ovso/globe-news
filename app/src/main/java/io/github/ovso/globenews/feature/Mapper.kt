package io.github.ovso.globenews.feature

import io.github.ovso.globenews.core.database.model.ArticleEntity
import io.github.ovso.globenews.core.network.ArticleResponse
import io.github.ovso.globenews.feature.home.ArticleUiModel

fun ArticleResponse.asArticleUiModel() = ArticleUiModel(
    publishedAt = publishedAt,
    title = title,
    url = url,
    urlToImage = urlToImage,
)

fun ArticleUiModel.asArticleEntity() = ArticleEntity(
    publishedAt = publishedAt,
    title = title,
    url = url,
    urlToImage = urlToImage,
    viewed = viewed,
)

fun ArticleResponse.asArticleEntity() = ArticleEntity(
    publishedAt = publishedAt,
    title = title,
    url = url,
    urlToImage = urlToImage,
    viewed = false,
)
fun ArticleEntity.asArticleUiModel() = ArticleUiModel(
    publishedAt = publishedAt,
    title = title,
    url = url,
    urlToImage = urlToImage,
    viewed = viewed,
)