package com.syntxr.michishirube.presentation.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syntxr.michishirube.data.factory.ViewModelFactory
import com.syntxr.michishirube.presentation.pages.home.component.ItemPerawi
import com.syntxr.michishirube.presentation.pages.home.event.HomeEvent
import com.syntxr.michishirube.presentation.pages.home.state.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory.getInstance()),
) {

    val state by viewModel.state.collectAsState()
    val list by viewModel.listPerawi.collectAsState()

    val snackBarHostState = SnackbarHostState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(HomeEvent.RetrievePerawi)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Perawi",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            when (val homeState = state) {
                is HomeState.Error -> {
                    LaunchedEffect(key1 = true) {
                        snackBarHostState.showSnackbar(homeState.msg)
                    }
                }

                HomeState.Loading -> {

                }

                HomeState.Success -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(3),
                        content = {
                            items(list){ perawi ->
                                ItemPerawi(
                                    name = perawi.name,
                                    hadits = perawi.hadist
                                )
                            }
                        }
                    )
                }

                null -> {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}