package com.syntxr.michishirube.presentation.pages.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.syntxr.michishirube.MichishirubeApplication
import com.syntxr.michishirube.data.factory.viewModelFactory
import com.syntxr.michishirube.presentation.pages.destinations.HaditsScreenDestination
import com.syntxr.michishirube.presentation.pages.hadits.HaditsScreenNavArgs
import com.syntxr.michishirube.presentation.pages.list.component.ItemListHadits
import java.util.Locale

data class ListHaditsScreenNavArgs(
    val perawi: String,
)

@Destination(
    navArgsDelegate = ListHaditsScreenNavArgs::class
)
@Composable
fun ListHadistScreen(
    navigator: DestinationsNavigator,
    viewModel: ListHaditsViewModel = viewModel(
        factory = viewModelFactory { stateHandle ->
            ListHaditsViewModel(
                MichishirubeApplication.repository,
                stateHandle
            )
        }
    ),
) {


    val listHadits = viewModel.haditsList.collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
        ) {
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterStart),
                onClick = {
                    navigator.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "btn_back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = viewModel.perawi.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    content = {

                        items(
                            listHadits.itemCount,
                            key = { listHadits[it]?.nomor!! },
                            contentType = listHadits.itemContentType { "haditsListPaging" }
                        ) { index ->
                            val hadits = listHadits[index]
                            ItemListHadits(
                                modifier = Modifier.clickable {
                                    navigator.navigate(
                                        HaditsScreenDestination(
                                            HaditsScreenNavArgs(
                                                viewModel.perawi,
                                                hadits?.nomor!!
                                            )
                                        )
                                    )
                                },
                                riwayat = hadits?.riwayat!!
                            )
                        }

                        when (listHadits.loadState.refresh) {
                            is LoadState.Error -> item{

                                Toast.makeText(
                                    LocalContext.current,
                                    "error when fetching API",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Column(
                                    Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Button(
                                        onClick = { listHadits.retry() }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            }

                            LoadState.Loading -> item {
                                Column(
                                    Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "Loading...",
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }
                            else -> {}
                        }

                        when (listHadits.loadState.append) {
                            is LoadState.Error -> item{

                                Toast.makeText(
                                    LocalContext.current,
                                    "error to fetch data",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Column(
                                    Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Button(
                                        onClick = { listHadits.retry() }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            }

                            LoadState.Loading -> item {
                                Column(
                                    Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "Loading...",
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }

                            else -> {}
                        }


                    }
                )
            }

        }

    }
}


@Preview
@Composable
fun ListHadistScreenPreview() {
}