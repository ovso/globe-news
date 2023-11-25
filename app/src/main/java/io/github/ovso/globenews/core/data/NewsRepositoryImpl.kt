package io.github.ovso.globenews.core.data

import io.github.ovso.globenews.core.network.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
) : NewsRepository {
    override suspend fun getNews(): List<Article> = remoteDataSource.getNews()
}