package com.syntxr.michishirube.presentation.pages.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemListHadits(
    riwayat : String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = riwayat,
            fontSize = 22.sp
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListHadistPreview() {
//    ItemListHadits()
}