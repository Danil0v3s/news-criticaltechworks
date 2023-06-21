package br.com.firstsoft.feature.main.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.firstsoft.controlarr.navigation.AppNavGraph
import br.com.firstsoft.core.ui.ControlArrTheme
import br.com.firstsoft.feature.security.api.SecurityApi
import br.com.firstsoft.feature.security.api.SecurityResult
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch

suspend fun requestAuthentication(securityApi: SecurityApi): Boolean {
    return when (securityApi.requestAuthentication()) {
        SecurityResult.Error -> false
        SecurityResult.Failure -> false
        SecurityResult.NotAvailable -> true
        SecurityResult.Success -> true
    }
}

@Composable
fun MainApp(moshi: Moshi, securityApi: SecurityApi) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    var isAuthenticated by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }

    LaunchedEffect(Unit) {
        isAuthenticated = requestAuthentication(securityApi)
    }

    ControlArrTheme {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background
        ) {
            if (isAuthenticated) {
                AppNavGraph(moshi)
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            isAuthenticated = requestAuthentication(securityApi)
                        }
                    }) {
                        Text(text = "Authenticate")
                    }
                }
            }
        }
    }
}
