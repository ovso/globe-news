package io.github.ovso.globenews.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.globenews.core.data.OfflineNewsRepository
import io.github.ovso.globenews.core.domain.GetNewsUseCase
import io.github.ovso.globenews.feature.asArticleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val offlineNewsRepository: OfflineNewsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() = viewModelScope.launch {
        getNewsUseCase().collect { result ->
            result.onSuccess { articles ->
                _uiState.update { HomeUiState.Success(articles) }
            }
            result.onFailure { throwable ->
                println(throwable.message)
                _uiState.update { HomeUiState.Success(emptyList()) }
            }
        }
    }

    fun onEvent(event: OnEvent) {
        when (event) {
            is OnEvent.OnItemClick -> {
                viewModelScope.launch {
                    offlineNewsRepository.upsert(
                        event.article.copy(viewed = 1).asArticleEntity()
                    )
                }
            }
        }
    }
}

sealed interface OnEvent {
    data class OnItemClick(val article: ArticleUiModel) : OnEvent
}

sealed interface HomeUiState {
    data class Success(val articles: List<ArticleUiModel>) : HomeUiState
    data object Loading : HomeUiState
}
