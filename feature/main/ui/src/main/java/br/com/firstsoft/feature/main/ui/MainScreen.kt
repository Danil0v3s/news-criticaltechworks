package br.com.firstsoft.feature.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.firstsoft.core.ui.ComposableScreen
import br.com.firstsoft.feature.home.ui.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    onItemSelected: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState(initial = MainViewModel.EMPTY_STATE)
    val pagerState = rememberPagerState()

    scope.launch { pagerState.animateScrollToPage(state.value.currentPageIndex) }

    Column {
        ViewPager(
            pagerState = pagerState,
            pages = state.value.pages,
            modifier = Modifier.weight(1f),
            onItemSelected = onItemSelected,
        )
        TabLayout(
            pagerState = pagerState,
            pages = state.value.pages,
            onTabClicked = viewModel::setCurrentPage
        )
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun ViewPager(
    pages: List<ComposableScreen>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit,
) {
    HorizontalPager(
        count = pages.size,
        state = pagerState,
        modifier = modifier
    ) { index ->
        when (pages[index]) {
            is HomeScreen -> HomeScreen(onItemSelected = onItemSelected)
        }
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun TabLayout(
    pagerState: PagerState,
    pages: List<ComposableScreen>,
    onTabClicked: (index: Int) -> Unit
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        pages.forEachIndexed { index, page ->
            Tab(
                text = { Text(page.title) },
                selected = pagerState.currentPage == index,
                onClick = { onTabClicked(index) },
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen({})
}
