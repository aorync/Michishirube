package com.syntxr.michishirube.domain.repository

import androidx.paging.PagingData
import com.syntxr.michishirube.domain.model.Perawi
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.model.response.Item
import com.syntxr.michishirube.domain.model.response.ListHaditsResponse
import kotlinx.coroutines.flow.Flow

interface HaditsRepository {
    fun getListHadits(perawi : String) : Flow<PagingData<Item>>

    suspend fun getHadits(perawi: String, no : Int) : HaditsResponse

    fun listPerawi() : List<Perawi>
}