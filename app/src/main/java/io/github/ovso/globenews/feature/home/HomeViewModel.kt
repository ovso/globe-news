package io.github.ovso.globenews.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.globenews.core.data.NewsRepository
import io.github.ovso.globenews.core.network.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    init {
        Log.d("HomeViewModel", "init: ")
    }
}


sealed interface HomeUiState {
    data class Success(val articles: List<Article>) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}
