package com.syntxr.michishirube.presentation.pages.hadits.event

sealed class HaditsEvent {
    data class RetrieveHadits(val perawi : String, val no : Int) : HaditsEvent()
}
