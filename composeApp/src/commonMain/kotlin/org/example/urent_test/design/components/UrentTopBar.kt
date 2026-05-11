package org.example.urent_test.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.jetbrains.compose.resources.vectorResource
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.back_arrow

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        vectorResource(Res.drawable.back_arrow),
                        contentDescription = "back button",
                        modifier = Modifier
                            .size(24.dp),
                        tint = ColorTokens.IconDefault
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
        Text(
            text = title,
            color = ColorTokens.Text.Primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview
@Composable
private fun TestTopBarPreview() {
    UrentTestTheme {
        ColumnPreview {
            TopBar(title = "Список городов")
            TopBar(title = "Информация о городе", onBackClick = {})
        }
    }
}
