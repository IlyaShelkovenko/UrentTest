package org.example.urent_test.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.example.urent_test.design.tokens.DimensionTokens
import org.jetbrains.compose.resources.vectorResource
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.search_icon

@Composable
fun UrentSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Введите название города",
    focused: Boolean? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val showFocused = focused ?: isFocused
    val borderColor = if (showFocused) {
        ColorTokens.Input.BorderFocused
    } else {
        ColorTokens.Input.BorderDefault
    }
    val shape = RoundedCornerShape(DimensionTokens.InputRadius)
    val textStyle = MaterialTheme.typography.titleMedium.copy(color = ColorTokens.Text.Primary)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth().height(DimensionTokens.InputHeight),
        singleLine = true,
        textStyle = textStyle,
        cursorBrush = SolidColor(ColorTokens.Accent),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .clip(shape)
                    .background(ColorTokens.Input.Background)
                    .border(
                        width = DimensionTokens.InputBorderWidth,
                        color = borderColor,
                        shape = shape
                    )
                    .defaultMinSize(minHeight = DimensionTokens.InputHeight)
                    .padding(
                        horizontal = DimensionTokens.InputHorizontalPadding,
                        vertical = DimensionTokens.InputVerticalPadding
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = ColorTokens.Text.Placeholder,
                            style = textStyle
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = vectorResource(Res.drawable.search_icon),
                    contentDescription = "search_icon",
                    modifier = Modifier.size(DimensionTokens.InputIconSize),
                    tint = ColorTokens.IconDefault
                )
            }
        }
    )
}

@Preview
@Composable
private fun UrentTestSearchFieldPreview() {
    UrentTestTheme {
        ColumnPreview {
            UrentSearchField(
                value = "",
                onValueChange = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
                focused = false
            )
            UrentSearchField(
                value = "",
                onValueChange = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
                focused = true
            )
            UrentSearchField(
                value = "Мос",
                onValueChange = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
                focused = true
            )
            UrentSearchField(
                value = "Москва",
                onValueChange = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
                focused = true
            )
            UrentSearchField(
                value = "Москва",
                onValueChange = {},
                modifier = Modifier.width(DimensionTokens.ComponentPreviewWidth),
                focused = false
            )
        }
    }
}
