package com.syntxr.michishirube.domain.model.response


import com.google.gson.annotations.SerializedName

data class HaditsResponse(
    @SerializedName("arabic")
    val arabic: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("nomor")
    val nomor: Int? = 0,
    @SerializedName("perawi")
    val perawi: String? = "",
    @SerializedName("riwayat")
    val riwayat: String? = "",
    @SerializedName("terjemahan")
    val terjemahan: String? = ""
)