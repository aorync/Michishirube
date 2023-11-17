package com.syntxr.michishirube

import android.app.Application
import com.syntxr.michishirube.data.repository.HaditsRepositoryImpl
import com.syntxr.michishirube.data.source.local.Perawis
import com.syntxr.michishirube.di.AppModule
import com.syntxr.michishirube.di.AppModuleImpl
import com.syntxr.michishirube.domain.repository.HaditsRepository

class MichishirubeApplication : Application() {
    companion object{
        lateinit var appModule: AppModule
        lateinit var repository: HaditsRepository
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
        repository = HaditsRepositoryImpl(appModule.api, Perawis)
    }
}