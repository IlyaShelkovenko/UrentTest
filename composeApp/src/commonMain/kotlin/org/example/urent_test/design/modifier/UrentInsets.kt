package org.example.urent_test.design.modifier

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.screenInsets(): Modifier = windowInsetsPadding(WindowInsets.safeDrawing)
