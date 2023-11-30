package com.syntxr.michishirube.presentation.pages.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.syntxr.michishirube.MichishirubeApplication
import com.syntxr.michishirube.data.factory.viewModelFactory
import com.syntxr.michishirube.presentation.pages.destinations.ListHadistScreenDestination
import com.syntxr.michishirube.presentation.pages.home.component.ItemPerawi
import com.syntxr.michishirube.presentation.pages.list.ListHaditsScreenNavArgs

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = viewModel(
        factory = viewModelFactory {
            HomeViewModel(MichishirubeApplication.repository)
        }
    ),
) {

    val state by viewModel.state.collectAsState()
    val list by viewModel.listPerawi.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Perawi",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (val homeState = state) {
                    is HomeState.Error -> {
                        Toast.makeText(LocalContext.current, homeState.msg, Toast.LENGTH_SHORT)
                            .show()
                    }

                    HomeState.Loading -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading...",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    HomeState.Success -> {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            content = {
                                items(list) { perawi ->
                                    ItemPerawi(
                                        modifier = Modifier.clickable {
                                            navigator.navigate(
                                                ListHadistScreenDestination(
                                                    ListHaditsScreenNavArgs(perawi.id)
                                                )
                                            )
                                        },
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
}


@Preview
@Composable
fun PreviewHomeScreen() {
}