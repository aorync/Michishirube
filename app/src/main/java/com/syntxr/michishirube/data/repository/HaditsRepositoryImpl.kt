package com.syntxr.michishirube.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.syntxr.michishirube.data.paging.HaditsPagingSource
import com.syntxr.michishirube.data.source.local.Perawis
import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.model.Perawi
import com.syntxr.michishirube.domain.model.response.HaditsResponse
import com.syntxr.michishirube.domain.model.response.Item
import com.syntxr.michishirube.domain.repository.HaditsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class HaditsRepositoryImpl(
    private val api : ApiInterface,
    private val perawi : Perawis
) : HaditsRepository {
    override fun getListHadits(perawi : String) : Flow<PagingData<Item>> =
        Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                HaditsPagingSource(
                    api,
                    perawi
                )
            }
        ).flow.flowOn(Dispatchers.Default)

    override suspend fun getHadits(perawi: String, no: Int): HaditsResponse {
        return api.getHadits(perawi, no)
    }

    override fun listPerawi(): List<Perawi> {
        return perawi.listPerawi()
    }

}