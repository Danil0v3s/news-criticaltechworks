package br.com.firstsoft.feature.news.api.usecase

import br.com.firstsoft.core.common.Result
import br.com.firstsoft.core.usecase.SuspendUseCase
import br.com.firstsoft.feature.news.api.model.Article

interface FetchHeadlinesUseCase : SuspendUseCase<Result<List<Article>, Throwable>, FetchHeadlinesUseCase.Input> {
    data class Input(val query: String)
}
