package br.com.firstsoft.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firstsoft.core.common.Result
import br.com.firstsoft.feature.news.api.model.Article
import br.com.firstsoft.feature.news.api.usecase.FetchHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHeadlinesUseCase: FetchHeadlinesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: Flow<HomeState>
        get() = _state

    init {
        viewModelScope.launch {
            fetchLibrary()
        }
    }

    private suspend fun fetchLibrary() {
        _state.update { HomeState(loading = true) }
        when (val result = fetchHeadlinesUseCase(FetchHeadlinesUseCase.Input(""))) {
            is Result.Failure -> _state.update {
                HomeState(
                    error = HomeState.Error(
                        "31337",
                        "Something went wrong"
                    )
                )
            }

            is Result.Success -> {
                _state.update {
                    it.copy(
                        loading = false,
                        articles = result.value.sortedBy { article -> article.publishedAt },
                    )
                }
            }
        }
    }
}

data class HomeState(
    val loading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: Error? = null
) {
    data class Error(val code: String, val message: String)
}
