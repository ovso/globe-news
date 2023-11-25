package io.github.ovso.globenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.globenews.core.ui.theme.GlobenewsTheme
import io.github.ovso.globenews.feature.home.HomeScreen
import io.github.ovso.globenews.feature.home.HomeViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlobenewsTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: HomeViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsState()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HomeScreen(state = state,)
                }
            }
        }
    }
}