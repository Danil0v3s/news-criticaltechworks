package br.com.firstsoft.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

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
        delay(100)
        _state.update { it.copy(queueItems = listOf("a", "b", "c")) }
    }
}

data class HomeState(
    val queueItems: List<String> = emptyList(),
)
