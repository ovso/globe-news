package io.github.ovso.globenews.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.ovso.globenews.core.network.Article

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                text = "헤드라인",
                style = MaterialTheme.typography.headlineLarge
            )
        },
        content = {
            when (state) {
                is HomeUiState.Loading ->
                    HomeLoading(modifier = Modifier.padding(it))

                is HomeUiState.Success ->
                    HomeContent(modifier = Modifier.padding(it), state.articles)
            }
        }
    )

    BackHandler {
        (context as? Activity)?.finish()
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
    modifier: Modifier = Modifier,
    articles: List<Article>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(articles) { article ->
            ArticleCard(
                modifier = Modifier.height(300.dp),
                imgUrl = article.urlToImage.orEmpty(),
                title = article.title.orEmpty(),
                publishedAt = article.publishedAt.orEmpty()
            )
        }
    }
}

@Composable
private fun ArticleCard(
    modifier: Modifier,
    imgUrl: String,
    title: String,
    publishedAt: String,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {

                },
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    contentScale = ContentScale.FillWidth,
                    model = imgUrl,
                    contentDescription = "썸네일 이미지"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = publishedAt,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(state = HomeUiState.Loading)
}

