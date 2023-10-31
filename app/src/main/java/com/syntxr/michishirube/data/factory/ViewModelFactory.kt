package com.syntxr.michishirube.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syntxr.michishirube.di.Injection
import com.syntxr.michishirube.domain.repository.HaditsRepository
import com.syntxr.michishirube.presentation.pages.hadits.HaditsViewModel
import com.syntxr.michishirube.presentation.pages.home.HomeViewModel
import com.syntxr.michishirube.presentation.pages.list.ListHaditsViewModel

class ViewModelFactory(
    private val repository: HaditsRepository,
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ViewModelFactory(Injection.provideHadistRepository())
        }.also { INSTANCE = it }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(ListHaditsViewModel::class.java) -> ListHaditsViewModel(repository) as T
            modelClass.isAssignableFrom(HaditsViewModel::class.java) -> HaditsViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class:  " + modelClass.name)
        }
    }
}