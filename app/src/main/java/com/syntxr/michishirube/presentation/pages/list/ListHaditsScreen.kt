package com.syntxr.michishirube.presentation.pages.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syntxr.michishirube.data.factory.ViewModelFactory
import com.syntxr.michishirube.presentation.pages.list.component.ItemListHadits
import com.syntxr.michishirube.presentation.pages.list.event.ListHaditsEvent
import com.syntxr.michishirube.presentation.pages.list.state.ListHaditsState
import java.util.Locale

@Composable
fun ListHadistScreen(
    viewModel: ListHaditsViewModel = viewModel(factory = ViewModelFactory.getInstance()),
) {

    val state by viewModel.state.collectAsState()
    val listHadits by viewModel.listHadits.collectAsState()
    val perawi = "bukhari"

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ListHaditsEvent.RetrieveListHadits(perawi))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = perawi.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
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
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                when (val listHaditsState = state) {
                    is ListHaditsState.Error -> {
                        Toast.makeText(
                            LocalContext.current,
                            listHaditsState.msg,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ListHaditsState.Loading -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading..."
                        )
                    }

                    ListHaditsState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            content = {
                                items(listHadits) { hadits ->
                                    ItemListHadits(
                                        riwayat = hadits.riwayat
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
fun ListHadistScreenPreview() {
    ListHadistScreen()
}