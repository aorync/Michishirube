package com.syntxr.michishirube.data.repository

import com.syntxr.michishirube.data.source.local.ListPerawi
import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.model.Perawi
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.model.response.ListHaditsResponse
import com.syntxr.michishirube.domain.repository.HaditsRepository

class HaditsRepositoryImpl(
    private val api : ApiInterface,
    private val perawi : ListPerawi
) : HaditsRepository {
    override suspend fun getListHadits(perawi : String) : ListHaditsResponse {
        return api.getHaditsList(perawi)
    }

    override suspend fun getHadits(perawi: String, no: Int): HaditsResponse {
        return api.getHadits(perawi, no)
    }

    override fun listPerawi(): List<Perawi> {
        return perawi.listPerawi()
    }

}