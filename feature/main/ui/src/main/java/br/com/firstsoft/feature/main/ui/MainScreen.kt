package br.com.firstsoft.feature.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.firstsoft.core.ui.ComposableScreen
import br.com.firstsoft.feature.home.ui.HomeScreen
import br.com.firstsoft.feature.news.api.model.Article
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

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
