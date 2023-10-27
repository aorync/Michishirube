package com.syntxr.michishirube.domain.model


import com.google.gson.annotations.SerializedName

data class HaditsResponse(
    @SerializedName("arabic")
    val arabic: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nomor")
    val nomor: Int,
    @SerializedName("perawi")
    val perawi: String,
    @SerializedName("riwayat")
    val riwayat: String,
    @SerializedName("terjemahan")
    val terjemahan: String
)