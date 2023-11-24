package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.Article
import io.github.ovso.globenews.core.network.NewsNetworkApi

interface NewsRemoteDataSource {
    suspend fun getNews(): List<Article>
}
