package com.syntxr.michishirube.domain.repository

import com.syntxr.michishirube.domain.model.Perawi
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.model.response.ListHaditsResponse
import kotlinx.coroutines.flow.Flow

interface HaditsRepository {
    suspend fun getListHadits(perawi : String) : ListHaditsResponse

    suspend fun getHadits(perawi: String, no : Int) : HaditsResponse

    fun listPerawi() : List<Perawi>
}