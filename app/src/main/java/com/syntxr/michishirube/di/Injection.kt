package com.syntxr.michishirube.di

import com.syntxr.michishirube.data.repository.HaditsRepositoryImpl
import com.syntxr.michishirube.data.source.local.ListPerawi
import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.repository.HaditsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {
    private fun provideApi() : ApiInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hadits-api.superxdev.repl.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    fun provideHadistRepository() : HaditsRepository{
        val api = provideApi()
        val perawi = ListPerawi
        return HaditsRepositoryImpl(api, perawi)
    }
}