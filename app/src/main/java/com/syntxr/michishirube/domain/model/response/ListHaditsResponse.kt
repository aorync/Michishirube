package com.syntxr.michishirube.domain.model.response


import com.google.gson.annotations.SerializedName

data class ListHaditsResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("meta")
    val meta: Meta
)