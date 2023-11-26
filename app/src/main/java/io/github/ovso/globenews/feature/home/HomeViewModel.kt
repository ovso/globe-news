package io.github.ovso.globenews.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.globenews.core.data.NewsRepository
import io.github.ovso.globenews.core.data.OfflineNewsRepository
import io.github.ovso.globenews.core.utils.NetworkUtils
import io.github.ovso.globenews.feature.asArticleEntity
import io.github.ovso.globenews.feature.asArticleUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val offlineNewsRepository: OfflineNewsRepository,
    private val networkUtils: NetworkUtils,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        when (networkUtils.isNetworkAvailable()) {
            true -> fetchTopHeadlines()
            false -> fetchOfflineTopHeadlines()
        }
    }

    private fun fetchOfflineTopHeadlines() = viewModelScope.launch {
        offlineNewsRepository.getArticles().collect { articles ->
            _uiState.update { HomeUiState.Success(articles.map { it.asArticleUiModel() }) }
        }
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            val news = newsRepository.getTopHeadlines()
            _uiState.update { HomeUiState.Success(news.map { it.asArticleUiModel() }) }
            offlineNewsRepository.insertAll(news.map { it.asArticleEntity() })
        }
    }
}


sealed interface HomeUiState {
    data class Success(val articles: List<ArticleUiModel>) : HomeUiState
    data object Loading : HomeUiState
}
