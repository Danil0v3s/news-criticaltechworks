package br.com.firstsoft.core.network.api

import br.com.firstsoft.core.network.api.dto.NewsResponseDto
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class NewsNetworkDataSource @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun fetchHeadlines(
        query: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): ApiResult<NewsResponseDto, Throwable> =
        newsApi.fetchHeadlines(query = query, pageSize = pageSize, page = page)
}
