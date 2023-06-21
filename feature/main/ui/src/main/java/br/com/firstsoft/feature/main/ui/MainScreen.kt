package br.com.firstsoft.feature.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.firstsoft.feature.home.ui.HomeScreen
import br.com.firstsoft.feature.news.api.model.Article
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    onItemSelected: (Article) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState(initial = MainViewModel.EMPTY_STATE)

    HomeScreen(onItemSelected = onItemSelected)
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen({})
}
