package com.syntxr.michishirube.presentation.pages.hadits.state

sealed class HaditsState {
    object Loading : HaditsState()
    object Success : HaditsState()
    data class Error(val msg : String) : HaditsState()
}
