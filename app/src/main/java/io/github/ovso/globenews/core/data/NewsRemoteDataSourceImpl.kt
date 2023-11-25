package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.Article
import io.github.ovso.globenews.core.network.NewsNetworkApi
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val api: NewsNetworkApi
): NewsRemoteDataSource {
    override suspend fun getNews(): List<Article> = api.getNews().articles
}