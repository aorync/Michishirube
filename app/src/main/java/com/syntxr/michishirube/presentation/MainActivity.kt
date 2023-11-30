package com.syntxr.michishirube.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ramcosta.composedestinations.DestinationsNavHost
import com.syntxr.michishirube.MichishirubeApplication
import com.syntxr.michishirube.presentation.pages.NavGraphs
import com.syntxr.michishirube.presentation.pages.home.HomeViewModel
import com.syntxr.michishirube.presentation.ui.theme.MichishirubeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel = HomeViewModel(MichishirubeApplication.repository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.loading.value
            }
        }

        setContent {
            MichishirubeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { innerPadding ->
                        DestinationsNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navGraph = NavGraphs.root
                        )
                    }
                }
            }
        }
    }
}