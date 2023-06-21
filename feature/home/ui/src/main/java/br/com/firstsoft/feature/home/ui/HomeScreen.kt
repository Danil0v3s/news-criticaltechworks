package br.com.firstsoft.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.firstsoft.core.ui.ComposableScreen
import br.com.firstsoft.feature.news.api.model.Article
import coil.compose.AsyncImage

object HomeScreen : ComposableScreen {
    override val title: String
        get() = "Headlines"
}

@Composable
fun HomeScreen(
    onItemSelected: (Article) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState(initial = HomeState(loading = true))

    HomeView(state.value, onItemSelected = onItemSelected)
}

@Composable
private fun HomeView(state: HomeState, onItemSelected: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        when {
            state.loading -> LoadingView()
            state.error != null -> ErrorView(state.error)
            else -> ContentView(state, onItemSelected)
        }
    }
}

@Composable
private fun ContentView(state: HomeState, onItemSelected: (Article) -> Unit) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.source_name), style = MaterialTheme.typography.h1)
    })

    if (state.articles.isEmpty()) {
        Text(text = "No articles found")
    } else {
        ItemsColumn(state = state, onItemSelected = onItemSelected)
    }
}

@Composable
private fun ErrorView(error: HomeState.Error) {
    Text(text = error.message)
}

@Composable
private fun LoadingView() {
    Text(text = "Loading...", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

@Composable
private fun ItemsColumn(state: HomeState, onItemSelected: (Article) -> Unit) {
    LazyColumn {
        items(state.articles.size) {
            val item = state.articles[it]
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { onItemSelected(item) },
                elevation = 8.dp,
            ) {
                Column {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = item.urlToImage,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )

                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = item.title, style = MaterialTheme.typography.h2)
                        Text(
                            text = item.description.orEmpty(),
                            style = MaterialTheme.typography.body1
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.author, style = MaterialTheme.typography.body2)
                            Text(
                                text = item.publishedAt.toString(),
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    }
}
