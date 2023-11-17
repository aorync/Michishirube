package com.syntxr.michishirube.presentation.pages.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemPerawi(
    name : String,
    hadits : Int,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .defaultMinSize(64.dp)
            .clip(RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier.padding(horizontal = 8.dp),
            text = name,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "$hadits \nhadits",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPerawiPreview() {
    ItemPerawi("bukhari", 1000)
}