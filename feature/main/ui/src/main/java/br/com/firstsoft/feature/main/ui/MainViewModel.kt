package br.com.firstsoft.feature.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firstsoft.core.ui.ComposableScreen
import br.com.firstsoft.feature.home.ui.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(EMPTY_STATE)
    val state: Flow<MainState>
        get() = _state

    init {
        viewModelScope.launch { isAnyServiceDataMissing() }
    }

    fun setCurrentPage(page: Int) {
        _state.update { it.copy(currentPageIndex = page) }
    }

    private suspend fun isAnyServiceDataMissing() = withContext(Dispatchers.IO) {
       /* val deferred = listOf(
            async { getSonarrApiKeyUseCase() },
            async { getRadarrApiKeyUseCase() },
            async { getSonarrHostAndPortUseCase() },
            async { getRadarrHostAndPortUseCase() }
        )

        val isAnyDataMissing = deferred.awaitAll().any { it == null }
        if (isAnyDataMissing) {
            _state.update { it.copy(currentPageIndex = pagerScreens.indexOf(SettingsScreen)) }
        }*/
    }

    companion object {
        val pagerScreens = listOf(
            HomeScreen
        )

        val EMPTY_STATE = MainState(pages = pagerScreens)
    }
}

data class MainState(
    val pages: List<ComposableScreen> = emptyList(),
    val currentPageIndex: Int = 0,
)
