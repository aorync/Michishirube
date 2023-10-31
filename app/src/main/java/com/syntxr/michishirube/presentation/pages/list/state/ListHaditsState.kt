package com.syntxr.michishirube.presentation.pages.list.state

sealed class ListHaditsState {
    object Loading : ListHaditsState()
    object Success : ListHaditsState()
    data class Error(val msg : String) : ListHaditsState()
}