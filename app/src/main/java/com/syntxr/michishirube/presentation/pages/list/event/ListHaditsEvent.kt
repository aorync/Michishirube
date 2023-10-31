package com.syntxr.michishirube.presentation.pages.list.event

sealed class ListHaditsEvent {
    data class RetrieveListHadits(val perawi : String) : ListHaditsEvent()
}