package com.syntxr.michishirube.presentation.pages.hadits

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.repository.HaditsRepository
import com.syntxr.michishirube.presentation.pages.navArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HaditsViewModel(
    private val repository: HaditsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow<HaditsState?>(null)
    val state = _state.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HaditsState.Loading
    )

    private val _hadits = MutableStateFlow(HaditsResponse())
    val hadits = _hadits.asStateFlow()

    val perawi = savedStateHandle.navArgs<HaditsScreenNavArgs>().perawi
    val noHadits = savedStateHandle.navArgs<HaditsScreenNavArgs>().noHadits

    private fun fetchHadit() {
        viewModelScope.launch {
            try {
                _state.emit(HaditsState.Loading)
                val data = repository.getHadits(perawi, noHadits)
                _hadits.emit(data)
                _state.emit(HaditsState.Success)
            } catch (e: Exception) {
                HaditsState.Error(e.message ?: "Unknown error")
            }
        }
    }

    init {
        fetchHadit()
    }
}


sealed class HaditsState {
    object Loading : HaditsState()
    object Success : HaditsState()
    data class Error(val msg: String) : HaditsState()
}
