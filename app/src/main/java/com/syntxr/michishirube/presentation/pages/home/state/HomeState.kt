package com.syntxr.michishirube.presentation.pages.home.state

sealed class HomeState {
    object Loading : HomeState()
    object Success : HomeState()
    data class Error(val msg : String) : HomeState()
}