package com.syntxr.michishirube.domain.model.response


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalPage")
    val totalPage: Int
)