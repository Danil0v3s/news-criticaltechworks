package br.com.firstsoft.feature.news.repository

import br.com.firstsoft.core.common.Result
import br.com.firstsoft.core.network.api.NewsNetworkDataSource
import br.com.firstsoft.core.network.toResult
import br.com.firstsoft.feature.news.api.model.Article
import br.com.firstsoft.feature.news.api.repository.NewsRepository
import br.com.firstsoft.feature.news.mapper.toDomain
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class NewsRepositoryImpl @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource
) : NewsRepository {
    override suspend fun fetchHeadlines(query: String): Result<List<Article>, Throwable> {
        return when (val result = newsNetworkDataSource.fetchHeadlines(query).toResult()) {
            is Result.Failure -> Result.Failure(result.error)
            is Result.Success -> Result.Success(result.value.toDomain())
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NewsRepositoryModule {
    @Binds
    abstract fun bind(impl: NewsRepositoryImpl): NewsRepository
}
