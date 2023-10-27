package com.syntxr.michishirube.data.repository

import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.model.HaditsResponse
import com.syntxr.michishirube.domain.model.ListHaditsResponse

class HaditsRepositoryImpl(
    private val api : ApiInterface
) {
    suspend fun getPerawiList(perawi : String) : ListHaditsResponse {
        return api.getPerawiList(perawi)
    }

    suspend fun getHadits(perawi: String, no : Int) : HaditsResponse {
        return api.getHadits(perawi, no)
    }
}