package br.com.firstsoft.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firstsoft.feature.news.api.usecase.FetchHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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
        fetchHeadlinesUseCase(FetchHeadlinesUseCase.Input(""))
    }
}

data class HomeState(
    val queueItems: List<String> = emptyList(),
)
