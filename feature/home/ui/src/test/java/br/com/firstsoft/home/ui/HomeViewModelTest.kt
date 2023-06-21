package br.com.firstsoft.home.ui

import app.cash.turbine.test
import br.com.firstsoft.core.common.Result
import br.com.firstsoft.feature.home.ui.HomeState
import br.com.firstsoft.feature.home.ui.HomeViewModel
import br.com.firstsoft.feature.news.api.model.Article
import br.com.firstsoft.feature.news.api.usecase.FetchHeadlinesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fetchHeadlinesUseCase = mockk<FetchHeadlinesUseCase> {
        coEvery { this@mockk(any()) } returns Result.Failure(Error("error"))
    }

    private lateinit var sut: HomeViewModel

    @Before
    fun setup() {
        createViewModel()
    }

    @Test
    fun `given fetchHeadlinesUseCase succeeds then set items to state`() = runTest {
        val articleList = listOf(article)
        coEvery { fetchHeadlinesUseCase(any()) } returns Result.Success(articleList)

        val sut = createViewModel()
        sut.state.test {
            assertEquals(articleList, awaitItem().articles)
        }
    }

    @Test
    fun `given fetchHeadlinesUseCase fails then set error to state`() = runTest {
        val error = HomeState.Error("31337", "Something went wrong")

        val sut = createViewModel()

        sut.state.test {
            assertEquals(HomeState(error = error), awaitItem())
        }
    }

    private fun createViewModel(): HomeViewModel {
        sut = HomeViewModel(fetchHeadlinesUseCase)
        return sut
    }

    companion object {
        val article = Article(
            publishedAt = Instant.now(),
            content = "",
            title = "",
            urlToImage = "",
            author = "",
            description = "",
            source = Article.ArticleSource("", "")
        )
    }
}