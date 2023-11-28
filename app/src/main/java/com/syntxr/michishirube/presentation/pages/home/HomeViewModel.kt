package com.syntxr.michishirube.presentation.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntxr.michishirube.domain.model.Perawi
import com.syntxr.michishirube.domain.repository.HaditsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HaditsRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _state = MutableStateFlow<HomeState?>(null)
    val state = _state.asStateFlow()

    private val _listPerawi = MutableStateFlow<List<Perawi>>(emptyList())
    val listPerawi = _listPerawi.asStateFlow()

    private fun fetchPerawi() {
        viewModelScope.launch {
            try {
                _state.emit(HomeState.Loading)
                val data = repository.listPerawi()
                _listPerawi.emit(data)
                _state.emit(HomeState.Success)
                _loading.emit(false)
            } catch (e: Exception) {
                _state.emit(HomeState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    init {
        fetchPerawi()
    }
}


sealed class HomeState {
    object Loading : HomeState()
    object Success : HomeState()
    data class Error(val msg: String) : HomeState()
}