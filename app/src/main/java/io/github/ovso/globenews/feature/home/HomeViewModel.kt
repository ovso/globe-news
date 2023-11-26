package io.github.ovso.globenews.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.globenews.core.data.NewsRepository
import io.github.ovso.globenews.core.network.ArticleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val  news = newsRepository.getTopHeadlines()
            _uiState.update {
                HomeUiState.Success(news)
            }
        }
    }
}


sealed interface HomeUiState {
    data class Success(val articles: List<ArticleResponse>) : HomeUiState
    data object Loading : HomeUiState
}
