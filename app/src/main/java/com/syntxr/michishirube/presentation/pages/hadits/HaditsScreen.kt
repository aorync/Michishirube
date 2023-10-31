package com.syntxr.michishirube.presentation.pages.hadits

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syntxr.michishirube.data.factory.ViewModelFactory
import com.syntxr.michishirube.presentation.pages.hadits.event.HaditsEvent
import com.syntxr.michishirube.presentation.pages.hadits.state.HaditsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadistScreen(
    viewModel: HaditsViewModel = viewModel(factory = ViewModelFactory.getInstance()),
) {

    val state by viewModel.state.collectAsState()
    val hadits by viewModel.hadits.collectAsState()
    val perawi = "bukhari"
    val no = 1

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(HaditsEvent.RetrieveHadits(perawi, no))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = hadits.riwayat ?: "",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when(val haditsState = state){
                    is HaditsState.Error -> {
                        Toast.makeText(LocalContext.current, haditsState.msg, Toast.LENGTH_SHORT).show()
                    }
                    HaditsState.Loading -> {}
                    HaditsState.Success -> {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = hadits.arabic ?: "",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = hadits.terjemahan ?: "",
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    null -> {}
                }
            }
        }
    }
}


@Preview
@Composable
fun HadistScreenPreview() {
    HadistScreen()
}