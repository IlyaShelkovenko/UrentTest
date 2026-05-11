package org.example.urent_test.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.example.urent_test.design.tokens.DimensionTokens
import org.jetbrains.compose.resources.vectorResource
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.location_icon

@Composable
fun ListItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionTokens.ListItemHeight)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.location_icon),
                contentDescription = "location_icon",
                modifier = Modifier.width(DimensionTokens.ListIconSize),
                tint = ColorTokens.IconMuted
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                color = ColorTokens.Text.Primary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = ColorTokens.Divider
        )
    }
}

@Preview
@Composable
private fun ListItemPreview() {
    UrentTestTheme {
        ColumnPreview {
            ListItem(title = "Москва, Россия", onClick = {})
            ListItem(title = "Лондон, Великобритания", onClick = {})
            ListItem(title = "Париж, Франция", onClick = {})
        }
    }
}
