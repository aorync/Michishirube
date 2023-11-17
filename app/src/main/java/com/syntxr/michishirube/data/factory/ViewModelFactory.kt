package com.syntxr.michishirube.data.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

@Suppress("UNCHECKED_CAST")
fun <VM: ViewModel> viewModelFactory(initializer: (SavedStateHandle)->VM): AbstractSavedStateViewModelFactory{
    return object: AbstractSavedStateViewModelFactory(){
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return initializer(handle) as T
        }
    }
}