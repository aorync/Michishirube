package com.syntxr.michishirube.domain.model.response


import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("id")
    val id: Int,
    @SerializedName("nomor")
    val nomor: Int,
    @SerializedName("riwayat")
    val riwayat: String,
)