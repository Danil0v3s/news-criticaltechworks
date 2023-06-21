package br.com.firstsoft.feature.news

import br.com.firstsoft.core.common.Result
import br.com.firstsoft.core.network.api.NewsNetworkDataSource
import br.com.firstsoft.core.network.api.dto.NewsResponseDto
import br.com.firstsoft.feature.news.api.model.Article
import br.com.firstsoft.feature.news.repository.NewsRepositoryImpl
import com.slack.eithernet.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewsRepositoryImplTest {

    private val newsNetworkDataSource = mockk<NewsNetworkDataSource>()

    private lateinit var sut: NewsRepositoryImpl

    @Before
    fun setup() {
        sut = NewsRepositoryImpl(newsNetworkDataSource)
    }

    @Test
    fun `given api succeeds then map to domain correctly`() = runTest {
        val response = NewsResponseDto(
            articles = listOf(articleDTO),
            code = null,
            message = null,
            status = "ok",
            totalResults = 1
        )
        coEvery {
            newsNetworkDataSource.fetchHeadlines(
                any(),
                any(),
                any()
            )
        } returns ApiResult.success(response)

        val result = sut.fetchHeadlines("")

        assertTrue { result is Result.Success }
        assertEquals(listOf(article), (result as Result.Success).value)
    }

    @Test
    fun `given api fails then return failure`() = runTest {
        coEvery {
            newsNetworkDataSource.fetchHeadlines(
                any(),
                any(),
                any()
            )
        } returns ApiResult.unknownFailure(Throwable("test error"))

        val result = sut.fetchHeadlines("")

        assertTrue { result is Result.Failure }
    }

    companion object {
        val instant = Instant.now()
        val article = Article(
            publishedAt = instant,
            content = "content",
            title = "title",
            urlToImage = "image",
            author = "author",
            description = "description",
            source = Article.ArticleSource("id", "name")
        )

        val articleDTO = NewsResponseDto.ArticleDto(
            publishedAt = instant,
            content = "content",
            title = "title",
            urlToImage = "image",
            author = "author",
            description = "description",
            source = NewsResponseDto.ArticleDto.ArticleSourceDto("id", "name")
        )
    }
}
