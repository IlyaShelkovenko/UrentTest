package org.example.urent_test

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.urent_test.core.network.di.networkModule
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(
            declaration = {
                modules(
                    networkModule
                )
            }
        ), content = {
            MaterialTheme {
                AppScreen()
            }
        }
    )
}

@Composable
@Preview
fun AppScreen() {

}

