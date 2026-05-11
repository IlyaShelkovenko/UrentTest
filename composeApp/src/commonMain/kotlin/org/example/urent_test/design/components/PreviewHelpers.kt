package org.example.urent_test.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.tokens.ColorTokens

@Composable
internal fun ColumnPreview(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .background(ColorTokens.Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        content()
    }
}
