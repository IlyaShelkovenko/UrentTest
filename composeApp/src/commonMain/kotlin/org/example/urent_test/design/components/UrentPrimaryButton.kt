package org.example.urent_test.design.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.example.urent_test.design.tokens.DimensionTokens

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    pressed: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val containerColor = if (pressed || isPressed) {
        ColorTokens.Button.Pressed
    } else {
        ColorTokens.Button.Default
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = DimensionTokens.ButtonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(DimensionTokens.ButtonRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = ColorTokens.Text.OnAction,
            disabledContainerColor = ColorTokens.SurfacePressed,
            disabledContentColor = ColorTokens.TextSecondary
        ),
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    UrentTestTheme {
        ColumnPreview {
            PrimaryButton(
                text = "Поиск информации о городе",
                onClick = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth)
            )
            PressedUrentPrimaryButtonPreviewItem()
        }
    }
}

@Composable
private fun PressedUrentPrimaryButtonPreviewItem() {
    PrimaryButton(
        text = "Поиск информации о городе",
        onClick = {},
        modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
        pressed = true
    )
}
