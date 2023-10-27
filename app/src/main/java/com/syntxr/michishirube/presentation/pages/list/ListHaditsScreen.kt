package com.syntxr.michishirube.presentation.pages.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syntxr.michishirube.presentation.pages.list.component.ItemListHadits

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHadistScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(10){
                        ItemListHadits()
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ListHadistScreenPreview() {
    ListHadistScreen()
}