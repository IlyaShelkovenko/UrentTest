package org.example.urent_test

import androidx.compose.runtime.Composable
import org.example.urent_test.core.navigation.NavigationRoot
import org.example.urent_test.design.theme.UrentTestTheme

@Composable
fun App() {
    UrentTestTheme {
        NavigationRoot()
    }
}
