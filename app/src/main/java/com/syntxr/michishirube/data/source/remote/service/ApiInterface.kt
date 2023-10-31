package com.syntxr.michishirube.data.source.remote.service

import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.model.response.ListHaditsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    suspend fun getHaditsList(
        @Query("perawi") perawi : String
    ) : ListHaditsResponse

    @GET("/{perawi}/{nomor}")
    suspend fun getHadits(
        @Path("perawi") perawi: String,
        @Path("nomor") nomor: Int
    ) : HaditsResponse
}