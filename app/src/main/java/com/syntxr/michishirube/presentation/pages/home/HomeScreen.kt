package com.syntxr.michishirube.presentation.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syntxr.michishirube.presentation.pages.home.component.ItemPerawi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(

    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),

                content = {
                    items(8){
                        ItemPerawi()
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}