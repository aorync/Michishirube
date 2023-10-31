package com.syntxr.michishirube.presentation.pages.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntxr.michishirube.domain.model.response.Item
import com.syntxr.michishirube.domain.model.response.ListHaditsResponse
import com.syntxr.michishirube.domain.repository.HaditsRepository
import com.syntxr.michishirube.presentation.pages.home.state.HomeState
import com.syntxr.michishirube.presentation.pages.list.event.ListHaditsEvent
import com.syntxr.michishirube.presentation.pages.list.state.ListHaditsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListHaditsViewModel(
    private val repository: HaditsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<ListHaditsState?>(null)
    val state = _state.asStateFlow()

    private val _listHadits = MutableStateFlow<List<Item>>(emptyList())
    val listHadits = _listHadits.asStateFlow()

    fun onEvent(event: ListHaditsEvent){
        when(event){
            is ListHaditsEvent.RetrieveListHadits -> {
                viewModelScope.launch {
                    try {
                        _state.emit(ListHaditsState.Loading)
                        val data = repository.getListHadits(event.perawi)
                        _listHadits.emit(data.items)
                        _state.emit(ListHaditsState.Success)
                    }catch (e : Exception){
                        _state.emit(ListHaditsState.Error(e.message ?: "Unknown error"))
                    }
                }
            }
        }
    }
}