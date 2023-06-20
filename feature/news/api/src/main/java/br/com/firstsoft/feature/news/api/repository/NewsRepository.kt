package br.com.firstsoft.feature.news.api.repository

import br.com.firstsoft.core.common.Result
import br.com.firstsoft.feature.news.api.model.Article

interface NewsRepository {
    suspend fun fetchHeadlines(query: String): Result<List<Article>, Throwable>
}
