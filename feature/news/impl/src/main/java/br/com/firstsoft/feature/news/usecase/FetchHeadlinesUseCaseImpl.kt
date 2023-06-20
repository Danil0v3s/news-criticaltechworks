package br.com.firstsoft.feature.news.usecase

import br.com.firstsoft.core.common.Result
import br.com.firstsoft.feature.news.api.model.Article
import br.com.firstsoft.feature.news.api.repository.NewsRepository
import br.com.firstsoft.feature.news.api.usecase.FetchHeadlinesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class FetchHeadlinesUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : FetchHeadlinesUseCase {
    override suspend fun invoke(params: FetchHeadlinesUseCase.Input): Result<List<Article>, Throwable> {
        return newsRepository.fetchHeadlines(params.query)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FetchHeadlinesUseCaseModule {
    @Binds
    abstract fun bind(impl: FetchHeadlinesUseCaseImpl): FetchHeadlinesUseCase
}
