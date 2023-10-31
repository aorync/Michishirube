package com.syntxr.michishirube.presentation.pages.hadits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.repository.HaditsRepository
import com.syntxr.michishirube.presentation.pages.hadits.event.HaditsEvent
import com.syntxr.michishirube.presentation.pages.hadits.state.HaditsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HaditsViewModel(
    private val repository: HaditsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HaditsState?>(null)
    val state = _state.asStateFlow()

    private val _hadits = MutableStateFlow(HaditsResponse())
    val hadits = _hadits.asStateFlow()

    fun onEvent(event: HaditsEvent){
        when(event){
            is HaditsEvent.RetrieveHadits -> {
                viewModelScope.launch {
                    try {
                        _state.emit(HaditsState.Loading)
                        val data = repository.getHadits(event.perawi, event.no)
                        _hadits.emit(data)
                        _state.emit(HaditsState.Success)
                    }catch (e : Exception){
                        HaditsState.Error(e.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}