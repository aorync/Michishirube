package com.syntxr.michishirube.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.syntxr.michishirube.data.source.remote.service.ApiInterface
import com.syntxr.michishirube.domain.model.response.Item
import java.lang.Exception

class HaditsPagingSource(
    private val api : ApiInterface,
    private val perawiId : String,
): PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let {  anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try{
            val page = params.key ?: 1
            val response = api.getHaditsList(
                perawi = perawiId,
                page = page
            ) ?: return  LoadResult.Error(Exception("Error to fetch Api result"))
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.items.isEmpty()) null else page.plus(1)
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}