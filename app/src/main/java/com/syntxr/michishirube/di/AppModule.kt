package com.syntxr.michishirube.di

import com.syntxr.michishirube.data.repository.HaditsRepositoryImpl
import com.syntxr.michishirube.data.source.local.Perawis
import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.repository.HaditsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val api : ApiInterface
    val repository : HaditsRepository
}

class AppModuleImpl : AppModule{
    override val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://hadits-api.superxdev.repl.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    override val repository: HaditsRepository by lazy {
        val listPerawi = Perawis
        HaditsRepositoryImpl(api, listPerawi)
    }

}