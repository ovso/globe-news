package io.github.ovso.globenews.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ovso.globenews.core.network.Article

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                text = "Headlines",
                style = MaterialTheme.typography.titleLarge
            )
        }
    ) {
        when (state) {
            is HomeUiState.Loading -> HomeLoading(modifier = Modifier.padding(it))

            is HomeUiState.Success -> HomeContent(modifier = Modifier.padding(it), state.articles)
        }
    }
}

@Composable
private fun HomeLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    articles: List<Article>,
) {
    Column {
        articles.forEach {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = it.title.orEmpty(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(state = HomeUiState.Loading)
}