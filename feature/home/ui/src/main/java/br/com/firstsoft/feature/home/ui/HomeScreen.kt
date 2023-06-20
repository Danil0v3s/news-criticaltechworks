package br.com.firstsoft.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.firstsoft.core.ui.ComposableScreen
import coil.compose.AsyncImage

object HomeScreen : ComposableScreen {
    override val title: String
        get() = "Home"
}

@Composable
fun HomeScreen(
    onItemSelected: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState(initial = HomeState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Text(text = "Home", style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Queue", style = MaterialTheme.typography.h2)
        if (state.value.queueItems.isEmpty()) {
            Text(text = "Your queue seems to be empty!")
        } else {
            ItemsRow(state = state.value, onItemSelected = onItemSelected)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Library", style = MaterialTheme.typography.h2)
        ItemsRow(state = state.value, onItemSelected = onItemSelected)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun ItemsRow(state: HomeState, onItemSelected: (String) -> Unit) {
    LazyRow {
        items(state.queueItems.size) {
            val item = state.queueItems[it]
            Card(
                backgroundColor = Color.Red,
                modifier = Modifier
                    .padding(4.dp)
                    .width(114.dp)
                    .aspectRatio(0.68f)
                    .clickable { onItemSelected(item) },
                elevation = 8.dp,
            ) {
                AsyncImage(
                    model = "",
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}
