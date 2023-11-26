package io.github.ovso.globenews.core.domain

import io.github.ovso.globenews.core.data.NewsRepository
import io.github.ovso.globenews.core.data.OfflineNewsRepository
import io.github.ovso.globenews.core.utils.NetworkUtils
import io.github.ovso.globenews.feature.asArticleEntity
import io.github.ovso.globenews.feature.asArticleUiModel
import io.github.ovso.globenews.feature.home.ArticleUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetNewsUseCase {
    operator fun invoke(): Flow<Result<List<ArticleUiModel>>>
}

class GetNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val offlineNewsRepository: OfflineNewsRepository,
    private val networkUtils: NetworkUtils,
) : GetNewsUseCase {
    override operator fun invoke(): Flow<Result<List<ArticleUiModel>>> = flow {
        if (networkUtils.isNetworkAvailable()) {
            val news = newsRepository.getTopHeadlines()
            val entities = news.map { it.asArticleEntity() }
            offlineNewsRepository.insertAll(entities)
            offlineNewsRepository.getArticles().collect {
                emit(Result.success(it.map { entity -> entity.asArticleUiModel() }))
            }
        } else {
            offlineNewsRepository.getArticles().collect { entities ->
                when (entities.isEmpty()) {
                    true -> emit(Result.failure(Throwable("No data")))

                    false -> emit(Result.success(entities.map { entity -> entity.asArticleUiModel() }))
                }
            }
        }
    }
}