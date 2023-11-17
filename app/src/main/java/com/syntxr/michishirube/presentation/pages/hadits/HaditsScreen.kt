package com.syntxr.michishirube.presentation.pages.hadits

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.syntxr.michishirube.MichishirubeApplication
import com.syntxr.michishirube.data.factory.viewModelFactory

data class HaditsScreenNavArgs(
    val perawi: String,
    val noHadits: Int,
)

@Destination(
    navArgsDelegate = HaditsScreenNavArgs::class
)
@Composable
fun HaditsScreen(
    viewModel: HaditsViewModel = viewModel(
        factory = viewModelFactory { stateHandle ->
            HaditsViewModel(
                MichishirubeApplication.repository,
                stateHandle
            )
        }
    ),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val hadits by viewModel.hadits.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val type = "text/plain"
    val subject = "Share hadits with..."
    val sharewith = "ShareWith"
    val sharedText = "${hadits.perawi} \n${hadits.arabic} \n${hadits.terjemahan}"


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
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = "btn_back",
                Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterStart),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = hadits.riwayat ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
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
                when (val haditsState = state) {
                    is HaditsState.Error -> {
                        Toast.makeText(LocalContext.current, haditsState.msg, Toast.LENGTH_SHORT)
                            .show()
                    }

                    HaditsState.Loading -> {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Loading..."
                            )
                        }
                    }

                    HaditsState.Success -> {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = hadits.arabic ?: "",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = hadits.terjemahan ?: "",
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ContentCopy,
                                contentDescription = "btn_copy",
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.clickable {
                                    clipboardManager.setText(AnnotatedString(sharedText))
                                    Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show()
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = "btn_share",
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.clickable {
                                    val intent = Intent(Intent.ACTION_SEND)
                                    intent.type = type
                                    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                                    intent.putExtra(Intent.EXTRA_TEXT, sharedText)
                                    ContextCompat.startActivity(
                                        context,
                                        Intent.createChooser(intent, sharewith),
                                        null
                                    )
                                }
                            )
                        }
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
    HaditsScreen()
}