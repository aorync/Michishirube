package com.syntxr.michishirube.presentation.pages.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntxr.michishirube.domain.model.response.Item
import com.syntxr.michishirube.domain.repository.HaditsRepository
import com.syntxr.michishirube.presentation.pages.navArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListHaditsViewModel(
    private val repository: HaditsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val perawi = savedStateHandle.navArgs<ListHaditsScreenNavArgs>().perawi
    var haditsList = repository.getListHadits(perawi)
}
