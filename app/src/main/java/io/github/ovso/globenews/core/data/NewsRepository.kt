package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.Article

interface NewsRepository {
    suspend fun getNews(): List<Article>
}