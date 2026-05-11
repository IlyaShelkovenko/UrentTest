package org.example.urent_test.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens

@Composable
fun CityInfoField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = ColorTokens.Text.Primary,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = value,
            color = ColorTokens.Text.Primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun CityInfoFieldPreview() {
    UrentTestTheme {
        ColumnPreview {
            CityInfoField(label = "Город", value = "Москва")
            CityInfoField(label = "Страна", value = "Россия")
            CityInfoField(label = "Население", value = "12 655 000 чел")
        }
    }
}
